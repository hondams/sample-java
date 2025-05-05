# コード管理

## 利用形態

- ファイル
  - 変更したファイルの配信が必要
    - 更新の検知が必要？
- Enum
  - 変更不可
- 【非推奨】DB
  - DB更新で変更可能
    - 更新の検知が必要？
- 【非推奨】application.properties
  - 環境変数で、変更可能
    - 再起動が必要？

## 実装

- Code
  - String code
  - String lable
- CodeSet
  - String codeType
  - `List<Code>` codes
- CodeSetFactory
  - String getCodeTypeName(String codeType);
  - `List<String>` getCodeTypes();
  - CodeSet getCodeSet(String codeType);

## マスタ変更通知

- ファイルマスタは、S3に格納。
  - 基本的に、起動時に読込利用
  - 起動中は、以下のようなコードで、実行中ノードに通知することで、マスタを更新する。

```java
import software.amazon.awssdk.services.ecs.EcsClient;
import software.amazon.awssdk.services.ecs.model.ListTasksRequest;
import software.amazon.awssdk.services.ecs.model.ListTasksResponse;
import software.amazon.awssdk.services.ecs.model.DescribeTasksRequest;
import software.amazon.awssdk.services.ecs.model.DescribeTasksResponse;
import software.amazon.awssdk.services.ec2.model.DescribeNetworkInterfacesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeNetworkInterfacesResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class ECSFargateTaskFetcher {
    public static void main(String[] args) {
        EcsClient ecsClient = EcsClient.create();
        String clusterName = "my-cluster";

        ListTasksRequest request = ListTasksRequest.builder()
                .cluster(clusterName)
                .build();

        ListTasksResponse response = ecsClient.listTasks(request);
        for (String taskArn : response.taskArns()) {
            DescribeTasksRequest request = DescribeTasksRequest.builder()
                    .cluster(clusterName)
                    .tasks(taskArn)
                    .build();
            DescribeTasksResponse response = ecsClient.describeTasks(request);
            for (Task task : response.tasks()) {
                for (var attachment : task.attachments()) {
                    for (var detail : attachment.details()) {
                        if ("networkInterfaceId".equals(detail.name())) {
                            String eniId = detail.value();
                            DescribeNetworkInterfacesRequest request = DescribeNetworkInterfacesRequest.builder()
                                    .networkInterfaceIds(eniId)
                                    .build();

                            DescribeNetworkInterfacesResponse response = ec2Client.describeNetworkInterfaces(request);
                            for (var eni : response.networkInterfaces()) {
                                String taskUri = "http://" + eni.association().publicIp();

                                HttpClient client = HttpClient.newHttpClient();
                                HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(taskUri))
                                        .GET()
                                        .build();

                                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                            }
                        }
                    }
                }
            }
        }
    }
}
```

## コード一覧

- [コード一覧](https://cio.go.jp/codes/)
- [標準ガイドライン群](https://cio.go.jp/guides/index.html#renkeimodel)
  - コード（分類体系）導入実践ガイドブック
  - 行政基本情報データ連携モデル
- [郵便番号データダウンロード](https://www.post.japanpost.jp/zipcode/download.html)
- [全国地方公共団体コード](https://www.soumu.go.jp/denshijiti/code.html)
- [オープンデータ](https://www.digital.go.jp/resources/open_data)
- [e-Gov データポータル](https://data.e-gov.go.jp/info/ja/top)
