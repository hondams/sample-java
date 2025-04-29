# ソースコードフォーマット

## 方針

- 基本的に、Google の styleguide に準拠したい
  - https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml

## vscode の問題点

- 標準の RedHat のプラグインのフォーマットは、うまく動作しない。
  - spotless を試す？
    - spotless for gradle というプラグインがある？
      - gradle でないとダメ？
      - vscode-gradle を使っているので、gradle のみ
    - Save で Maven のビルドを動かし
      - maven で設定する？
