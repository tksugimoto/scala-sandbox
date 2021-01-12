# クロスビルド

## 目的
- Scala 2.13 で開発したい
- project A で test に使用しているライブラリが Scala 2.12 しか対応してないが、使い続けたい

## 対策
main app を Scala 2.12, 2.13 両方対応にして
test 用 project から参照する

## 構成
- common: 内部共通 lib (Scala 2.12, 2.13 両対応)
- main: common に依存する app (Scala 2.12, 2.13 両対応)
- A: main に依存するtest専用project (Scala 2.12 only)
- B: main に依存するtest専用project (Scala 2.13 only)

## test
### Scala 2.12
```
sbt main/clean common/clean +A/test
``` 

### Scala 2.13
```
sbt main/clean common/clean +B/test
```

or 

```
sbt main/clean common/clean B/test
```

Scala 2.13 がデフォルトなので `B` は `+` なしでもOK
