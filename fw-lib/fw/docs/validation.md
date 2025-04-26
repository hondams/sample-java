# バリデーション

## 利用可能な入力チェックアノテーション

- 真偽値
  - サポートされている型
    - boolean および Boolean
  - 入力チェックアノテーション
    - AssertFalse
      - アノテーション付き要素は false でなければなりません。
    - AssertTrue
      - アノテーション付き要素は true でなければなりません。

- 数値・数値文字列
  - サポートされている型
    - BigDecimal
    - BigInteger
    - CharSequence
    - byte、short、int、long とそれぞれのラッパー
  - 入力チェックアノテーション
    - DecimalMax
      - アノテーション付き要素は、指定された最大値以下の値を持つ必要がある数値である必要があります。
    - DecimalMin
      - アノテーション付き要素は、指定された最小値以上の値を持つ必要がある数値である必要があります。
    - Digits
      - アノテーション付き要素は、許容範囲内の数値である必要があります。

- 数値
  - サポートされている型
    - BigDecimal
    - BigInteger
    - byte、short、int、long とそれぞれのラッパー
  - 入力チェックアノテーション
    - Max
      - アノテーション付き要素は、指定された最大値以下の値を持つ必要がある数値である必要があります。
    - Min
      - アノテーション付き要素は、指定された最小値以上の値を持つ必要がある数値である必要があります。
    - Negative
      - アノテーション付きの要素は、厳密に負の数でなければなりません（つまり
    - NegativeOrZero
      - アノテーション付き要素は負の数または 0 でなければなりません。
    - Positive
      - アノテーション付きの要素は、厳密に正の数でなければなりません（つまり
    - PositiveOrZero
      - アノテーション付き要素は正の数または 0 でなければなりません。

- 文字列
  - サポートされている型
    - CharSequence
  - 入力チェックアノテーション
    - Email
      - 文字列は整形式のメールアドレスである必要があります。
    - NotBlank
      - アノテーション付き要素は null であってはならず、空白文字以外の文字が少なくとも 1 つ含まれている必要があります。
    - Pattern
      - アノテーション付き CharSequence は、指定された正規表現と一致する必要があります。

- 年月日日時
  - サポートされている型
    - java.util.Date
    - java.util.Calendar
    - java.time.Instant
    - java.time.LocalDate
    - java.time.LocalDateTime
    - java.time.LocalTime
    - java.time.MonthDay
    - java.time.OffsetDateTime
    - java.time.OffsetTime
    - java.time.Year
    - java.time.YearMonth
    - java.time.ZonedDateTime
    - java.time.chrono.HijrahDate
    - java.time.chrono.JapaneseDate
    - java.time.chrono.MinguoDate
    - java.time.chrono.ThaiBuddhistDate
  - 入力チェックアノテーション
    - Future
      - アノテーション付き要素は、インスタント、日付、未来の時間である必要があります。
    - FutureOrPresent
      - アノテーション付き要素は、現在または未来のインスタント、日付、時刻である必要があります。
    - Past
      - アノテーション付き要素は、インスタント、日付、過去の時間である必要があります。
    - PastOrPresent
      - アノテーション付き要素は、過去または現在のインスタント、日付、時刻である必要があります。

- 文字列・コレクション
  - サポートされている型
    - CharSequence (文字シーケンスの長さが評価されます)
    - Collection (コレクションサイズが評価されます)
    - Map (マップサイズが評価されます)
    - 配列 (配列の長さが評価されます)
  - 入力チェックアノテーション
    - NotEmpty
      - アノテーション付き要素は null または空であってはなりません。
    - Size
      - アノテーション付き要素のサイズは、指定された境界（含まれる）の間にある必要があります。

- 任意
  - サポートされている型
    - すべて
  - 入力チェックアノテーション
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。

## Hibernate Validationの実装

- https://github.com/hibernate/hibernate-validator/blob/main/engine/src/main/java/org/hibernate/validator/internal/metadata/core/ConstraintHelper.java
  - アノテーションとConstraintValidatorの対応関係を構築

- https://github.com/hibernate/hibernate-validator/blob/main/engine/src/main/java/org/hibernate/validator/internal/engine/ValidatorFactoryImpl.java
  - 標準のアノテーションとConstraintValidatorを構築し、ValidatorImpl を生成

- 標準バリデーションのロジック変更は、難しそう・・・。

### custom validation develop guide

https://docs.jboss.org/hibernate/validator/9.0/reference/en-US/html_single/#validator-customconstraints

### TERASOLUNA 入力チェック

https://terasolunaorg.github.io/guideline/current/ja/ArchitectureInDetail/WebApplicationDetail/Validation.html

- 【TERASOLUNA】項目間比較（Compare）
- 【TERASOLUNA】文字列バイトサイズ範囲（ByteSize）、文字列バイトサイズ上限（ByteMax）、文字列バイトサイズ下限（ByteMin）

### Hibernate 入力チェック

- 【Hibernate validation】数値範囲
  - Hibernate validationに、Rangeがあるが、MinとMaxの合成アノテーションなので、使わない。

### アノテーションのパラメータに利用可能なデータ型

- https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.6.1
  - A primitive type
  - String
  - Class or an invocation of Class (§4.5)
  - An enum type
  - An annotation type
    - Object型？、アノテーション型
  - An array type whose component type is one of the preceding types (§10.1).

### メッセージファイルの優先順位

- https://qiita.com/kazuki43zoo/items/02f988217457421cc38f
  - クラスパス直下のValidationMessages.properties (複数あると1ファイルのみ有効)
  - クラスパス直下のContributorValidationMessages.properties (全ファイル有効)
  - クラスパス上のorg/hibernate/validator/ValidationMessages.properteis(HV提供のデフォルトのメッセージ定義ファイル)

### メッセージファイルのキー

https://terasolunaorg.github.io/guideline/current/ja/ArchitectureInDetail/WebApplicationDetail/Validation.html#application-messages-properties
https://docs.spring.io/spring-framework/docs/6.2.1/javadoc-api/org/springframework/validation/DefaultMessageCodesResolver.html

- springのmessages-propertiesに記載できるメッセージ
  - 項目名
    - メッセージキー
      - フォーム名（単純クラス名をLowerCamelCaseに変換）+ "." + フィールド名
      - フィールド名
        - こちらは、利用せず、フォームクラスごとに定義する。
  - 型変換できなかった時のエラーメッセージ
    - メッセージキー
      - "typeMismatch." + フィールドのデータ型（完全修飾クラス名）
    - 問題点
      - すべてのフィールドについて、チェックされない可能性がある？
      - エラーが発生した親要素もエラーになる？

- springのmessages-propertiesに記載できるが利用すべきでないメッセージ
  - バリデーションエラーの時のエラーメッセージ
    - メッセージキー
      - バリデーションアノテーション名（単純クラス名）+ "." + フィールド名
      - バリデーションアノテーション名（単純クラス名）+ "." + フィールド名
      - バリデーションアノテーション名（単純クラス名）+ "." + フィールドのデータ型（完全修飾クラス名）
    - 仕組み
      - 型ごとに、別のメッセージを定義可能
      - springのmessages.propertiesは、MessageSourceSupportで、MessageFormatとして解釈。
        - {0}：項目名、{1}～：アノテーションの属性値（属性名のアルファベット順）
          - 実際の制約注釈属性（つまり、「message」、「groups」、「payload」を除く）をすべて、属性名のアルファベット順に追加
            - https://docs.spring.io/spring-framework/docs/6.2.1/javadoc-api/org/springframework/validation/beanvalidation/SpringValidatorAdapter.html#getArgumentsForConstraint(java.lang.String,java.lang.String,jakarta.validation.metadata.ConstraintDescriptor)
    - 考察
      - {0}、{1}などしか解釈できず、EL式を利用できないので、「フィールドのデータ型（完全修飾クラス名）」ごとに、全パターンのメッセージは定義できない。

- バリデーションエラーの時のエラーメッセージ：？
  - 基本的に、ValidationMessages_ja.propertiesを使用。
    - Hibernate Validatorの仕様で、EL式が利用できる。
    - フィールドのデータ型ごとに、メッセージを切り替えるには、「フィールドのデータ型に応じて適切なメッセージを動的に選択する MessageInterpolator を実装」

### jakarta-expression-language

https://jakarta.ee/specifications/expression-language/4.0/jakarta-expression-language-spec-4.0#composite-expressions
