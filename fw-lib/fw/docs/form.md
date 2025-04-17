# 入力フォーム

## 入力フォームで利用可能なデータ型

- 真偽値
  - boolean/Boolean
    - 文字列形式：「true」または、「false」となるので、メッセージは、個別に設定する。
    - checkboxの標準文字列は、「on」となる。
      - [Checkbox Value](https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements/input/checkbox#value)
整数値
  - Integer
  - Long
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
  - Duration
    - 文字列形式：「ISO-8601デュレーション・フォーマットPnDTnHnMn.nS」となるので、基本的に利用しない
      - [Duration#parse(String)](https://docs.oracle.com/javase/jp/8/docs/api/java/time/Duration.html#parse-java.lang.CharSequence-)
- 複合データ型
  - データクラス
- リスト
  - List

## 入力フォームで利用可能な入力チェック

- 真偽値
  - boolean
    - AssertFalse
      - アノテーション付き要素は false でなければなりません。
    - AssertTrue
      - アノテーション付き要素は true でなければなりません。
  - Boolean
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - AssertFalse
      - アノテーション付き要素は false でなければなりません。
    - AssertTrue
      - アノテーション付き要素は true でなければなりません。
整数値
  - Integer
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - Max
      - アノテーション付き要素は、指定された最大値以下の値を持つ必要がある数値である必要があります。
    - Min
      - アノテーション付き要素は、指定された最小値以上の値を持つ必要がある数値である必要があります。
  - Long
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - DecimalMax
      - アノテーション付き要素は、指定された最大値以下の値を持つ必要がある数値である必要があります。
    - DecimalMin
      - アノテーション付き要素は、指定された最小値以上の値を持つ必要がある数値である必要があります。
  - BigInteger
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - DecimalMax
      - アノテーション付き要素は、指定された最大値以下の値を持つ必要がある数値である必要があります。
    - DecimalMin
      - アノテーション付き要素は、指定された最小値以上の値を持つ必要がある数値である必要があります。
- 小数値
  - BigDecimal
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - DecimalMax
      - アノテーション付き要素は、指定された最大値以下の値を持つ必要がある数値である必要があります。
    - DecimalMin
      - アノテーション付き要素は、指定された最小値以上の値を持つ必要がある数値である必要があります。
    - Digits
      - アノテーション付き要素は、許容範囲内の数値である必要があります。
- 文字列
  - String
    - NotNull
      - アノテーション付き要素は null であってはなりません。
    - NotEmpty
      - アノテーション付き要素は null または空であってはなりません。
    - 【使わない：文字列は、Trimする】NotBlank
      - アノテーション付き要素は null であってはならず、空白文字以外の文字が少なくとも 1 つ含まれている必要があります。
    - Size
      - アノテーション付き要素のサイズは、指定された境界（含まれる）の間にある必要があります。
    - Pattern
      - アノテーション付き CharSequence は、指定された正規表現と一致する必要があります。
- 年月日時分秒
  - LocalDateTime
    - NotNull
      - アノテーション付き要素は null であってはなりません。
- 年月日
  - LocalDate
    - NotNull
      - アノテーション付き要素は null であってはなりません。
- 時分秒
  - LocalTime
    - NotNull
      - アノテーション付き要素は null であってはなりません。
- 時間
  - Duration
    - NotNull
      - アノテーション付き要素は null であってはなりません。
- 文字列リスト
  - リスト要素には、文字列と同じバリデーションを指定可能

```java
@Valid
private List<@Pattern("[0-9]+") String> codes;
```

- 複合データ型リスト
  - List
    - 【使わない：基本的に、常に、非nullにする】NotNull
      - アノテーション付き要素は null であってはなりません。
    - NotEmpty
      - アノテーション付き要素は null または空であってはなりません。
    - Size
      - アノテーション付き要素のサイズは、指定された境界（含まれる）の間にある必要があります。
- 複合データ型
  - データクラス　※ ネストしたデータクラスを検証する場合は、`@Valid`を付与する。
    - NotNull
      - アノテーション付き要素は null であってはなりません。
