# ソースコードフォーマット

## 方針

- 基本的に、Google の styleguide に準拠したい
  - https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml

## vscode の問題点

- 標準の RedHat のプラグインのフォーマットは、うまく動作しない。
  - インデントが、空白8文字のままとなる。
- GradleとMavenは、Gradleを優先的に認識する。
  - フォーマットだけ、Gradleというわけには、いかない。
  - Spotless Gradle というプラグインがある？
    - うまく動せなかった。
    - また、拡張できるわけでもなく、保存したファイル以外も、フォーマットしそう。

## 解決策

- Mavenプロジェクトで、spotlessを構成する。
  - リファクタリングしつつ、フォーマットを適宜調整したいので、cleanthat、eclipseでフォーマットする。
- 「emeraldwalk.RunOnSave」プラグインを入れる。
  - 「emeraldwalk.RunOnSave」では、プロジェクトのルートフォルダでコマンドを実行する。
  - 「emeraldwalk.RunOnSave」では、ファイルパスしか指定できない。
    - spotlessでは、対象パスの正規表現のパターンが欲しい。
    - フルパスだと、ドライブ文字列の大文字・小文字がマッチしない。
      - 「emeraldwalk.RunOnSave」では、相対ファイルパスを指定する。
- プロジェクトのルートフォルダに、mvnのspotless:applyを実行するバッチファイルを配置する。
  - この中で、「emeraldwalk.RunOnSave」から渡された、ファイルパスを加工して、mvnのspotless:applyを実行する

```cmd
set FILE=%1
call mvn spotless:apply -DspotlessFiles=%FILE:\=\\%
```

- 「emeraldwalk.RunOnSave」として、以下を設定する。

```json
  "java.format.enabled": false,
  "emeraldwalk.runonsave": {
    "commands": [
      {
        "match": "\\.java$",
        "cmd": "spotless-apply-file .+${relativeFile}"
      },
    ],
  },
  "[java]": {
    "editor.formatOnSave": false,
  }
```
