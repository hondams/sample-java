# 入力フォーム

## 入力フォームで利用可能なデータ型

- 真偽値
  - boolean/Boolean
    - 文字列形式：「true」または、「false」となるので、メッセージは、個別に設定する。
    - checkboxの標準文字列は、「on」となる。
      - [Checkbox Value](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/input/checkbox#value)
整数値
  - Integer（9桁）
  - Long（18桁）
  - BigInteger
- 小数値
  - BigDecimal
- 文字列
  - String
- 年月日時分秒
  - LocalDateTime
- 年月日
  - LocalDate
- 時分秒
  - LocalTime
- 時間
  - 【利用しない】Duration
    - 文字列形式：「ISO-8601デュレーション・フォーマットPnDTnHnMn.nS」となるので、基本的に利用しない
      - [Duration#parse(String)](https://docs.oracle.com/javase/jp/8/docs/api/java/time/Duration.html#parse-java.lang.CharSequence-)
- 複合データ型
  - データクラス
- 複合データ型のリスト
  - List
- 文字列リスト
  - List

## 入力フォームで利用可能な入力チェック

- 真偽値
  - boolean（オン／オフで利用）
    - AssertFalse
      - アノテーション付き要素は false でなければなりません。
      - 【利用時注意】messageは、常に指定する。
    - AssertTrue
      - アノテーション付き要素は true でなければなりません。
      - 【利用時注意】messageは、常に指定する。
  - Boolean（Indeterminate Checkboxesなど、オン／オフ／ハーフチェックで利用）
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する
    - AssertFalse
      - アノテーション付き要素は false でなければなりません。
      - 【利用時注意】messageは、常に指定する。
    - AssertTrue
      - アノテーション付き要素は true でなければなりません。
      - 【利用時注意】messageは、常に指定する。
整数値
  - Integer
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する
    - 【追加】LongRange ※ Rangeが、Hibernate Validationにあるので、LongRangeとする。
      - アノテーション付き要素は、指定された最小値以上、最大値以下の値を持つ必要がある数値である必要があります。
    - 【使わない】Max
      - アノテーション付き要素は、指定された最大値以下の値を持つ必要がある数値である必要があります。
      - 【利用禁止】LongRangeを利用する。
    - 【使わない】Min
      - アノテーション付き要素は、指定された最小値以上の値を持つ必要がある数値である必要があります。
      - 【利用禁止】LongRangeを利用する。
  - Long
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する
    - 【追加】LongRange ※ Rangeが、Hibernate Validationにあるので、LongRangeとする。
      - アノテーション付き要素は、指定された最小値以上、最大値以下の値を持つ必要がある数値である必要があります。
    - 【使わない】Max
      - アノテーション付き要素は、指定された最大値以下の値を持つ必要がある数値である必要があります。
      - 【利用禁止】LongRangeを利用する。
    - 【使わない】Min
      - アノテーション付き要素は、指定された最小値以上の値を持つ必要がある数値である必要があります。
      - 【利用禁止】LongRangeを利用する。
  - BigInteger
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する
    - 【追加】DecimalRange
      - アノテーション付き要素は、指定された最小値以上、最大値以下の値を持つ必要がある数値である必要があります。
    - 【使わない】DecimalMax
      - アノテーション付き要素は、指定された最大値以下の値を持つ必要がある数値である必要があります。
      - 【利用禁止】DecimalRangeを利用する。
    - 【使わない】DecimalMin
      - アノテーション付き要素は、指定された最小値以上の値を持つ必要がある数値である必要があります。
      - 【利用禁止】DecimalRangeを利用する。
- 小数値
  - BigDecimal
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する
    - 【追加】DecimalRange
      - アノテーション付き要素は、指定された最小値以上、最大値以下の値を持つ必要がある数値である必要があります。
    - 【使わない】DecimalMax
      - アノテーション付き要素は、指定された最大値以下の値を持つ必要がある数値である必要があります。
      - 【利用禁止】DecimalRangeを利用する。
    - 【使わない】DecimalMin
      - アノテーション付き要素は、指定された最小値以上の値を持つ必要がある数値である必要があります。
      - 【利用禁止】DecimalRangeを利用する。
    - 【使わない】Digits
      - アノテーション付き要素は、許容範囲内の数値である必要があります。
      - 【利用禁止】小数部はFractionDigitsを利用し、整数部は、DecimalRangeを利用する
    - 【追加】FractionDigits
      - アノテーション付き要素は、許容範囲内の小数点以下桁数である必要があります。
- 文字列
  - String
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する
    - 【使わない】NotEmpty
      - アノテーション付き要素は null または空であってはなりません。
      - 【利用禁止】StringLengthを利用する。
    - 【使わない】NotBlank
      - アノテーション付き要素は null であってはならず、空白文字以外の文字が少なくとも 1 つ含まれている必要があります。
      - 【利用禁止】文字列は、常にTrimするので、NotNullを利用する。
    - 【使わない】Size
      - アノテーション付き要素のサイズは、指定された境界（含まれる）の間にある必要があります。
      - 【利用禁止】StringLengthを利用する。メッセージを使い分けるため、Listにのみ利用する。
    - 【追加】StringLength ※ Lengthが、Hibernate Validationにあるので、StringLengthとする。
      - アノテーション付き要素のサイズは、指定された境界（含まれる）の間にある必要があります。
    - 【追加】ByteLength
      - アノテーション付き要素のサイズは、指定された境界（含まれる）の間にある必要があります。
    - Pattern
      - アノテーション付き CharSequence は、指定された正規表現と一致する必要があります。
      - 【利用時注意】使う場合は、messageは、常に指定する。極力、正規表現の書き方で、脆弱性・性能劣化になることが多いので、ValueTypeの利用を推奨する。
    - 【追加】ValueType：非正規表現の文字列パターン
    - 【追加】CharType：文字種別
      - 半角数字、半角英字、半角小文字英字、半角大文字英字、半角記号
      - 全角数字、全角英字、全角小文字英字、全角大文字英字、全角記号
      - 半角文字、全角文字、数字、英字、英数字、記号
    - 【追加】CodeType：コード
- 年月日時分秒
  - LocalDateTime
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する
- 年月日
  - LocalDate
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する
- 時分秒
  - LocalTime
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する
- 【利用しない】時間
  - Duration
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する
- 文字列リスト
  - `List<String>`
    - NotNull
      - アノテーション付き要素は null であってはなりません。
      - 【利用時注意】基本的に、常に、非nullにするので、常に、付与する。
    - 【使わない】NotEmpty
      - アノテーション付き要素は null または空であってはなりません。
      - 【利用禁止】Sizeを利用する。
    - Size
      - アノテーション付き要素のサイズは、指定された境界（含まれる）の間にある必要があります。
    - リスト要素には、文字列と同じバリデーションを指定可能

```java
@Valid
private List<@Pattern("[0-9]+") String> codes;
```

- 複合データ型リスト
  - List
    - NotNull
      - アノテーション付き要素は null であってはなりません。
      - 【利用時注意】基本的に、常に、非nullにするので、常に、付与する。
    - 【使わない】NotEmpty
      - アノテーション付き要素は null または空であってはなりません。
      - 【利用禁止】Sizeを利用する。
    - Size
      - アノテーション付き要素のサイズは、指定された境界（含まれる）の間にある必要があります。
- 複合データ型
  - データクラス　※ ネストしたデータクラスを検証する場合は、`@Valid`を付与する。
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Null
      - アノテーション付き要素は null でなければなりません。
      - 【利用時注意】基本的に、グループと一緒に利用する

- 相関項目チェック
  - 項目間比較（Compare）

- その他チェック
  - これら以外は、メッセージ制御が難しいので、カスタムチェックとして実装する。

## 日付関連の値

- [標準ガイドライン群](https://cio.go.jp/guides/index.html)
  - 行政基本情報データ連携モデル
  - コード（分類体系）導入実践ガイドブック

## 正規表現の脆弱性

- [正規表現が ReDoS 脆弱になる 3 つの経験則](http://www.rcc.ritsumei.ac.jp/2021/1220_12435/)

## 空文字列、nullの扱い

- 空文字列、nullは、同じ値とみなす。
  - Oracleは、空文字列、nullは、値なしとして同じ意味となる。
  - Patternなどのバリデーションは、nullの場合、チェック、空文字はチェックするため、Springの機能で、空文字をnullに変換する。

```java
    @InitBinder()
    void allowEmptyDataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
```

## データ型チェック

- 問題点
  - JsonBodyなどは、データ型チェックが動作しない？
  - typeMismatchのチェックで、うまく、検知できない可能性がある？
- 対策
  - すべての項目を文字列で受け取り、バリデーションし、サービスの入力に、MapStructで変換する。
