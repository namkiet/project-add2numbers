# Project Add 2 Numbers - Core (MyBigNumber)

Nhánh `core`: kết quả **Task 1** của Remote Internship Program - Challenge for Software Developers (tài liệu `Add2Num` v1.8).
Ứng dụng Web (Task 2) nằm ở nhánh `main`.

## Cấu trúc

```text
project-add2numbers/
├── pom.xml
└── mybignumber-core/
    └── src/
        ├── main/java/com/internship/mybignumber/
        │   ├── MyBigNumber.java     # String sum(String stn1, String stn2) - cộng theo cột từ phải sang trái
        │   └── IStepListener.java   # Nhận lịch sử từng bước tính
        └── test/java/com/internship/mybignumber/
            └── MyBigNumberTest.java # 17 unit test, coverage 100%
```

## Yêu cầu môi trường
- JDK 21+ (`java -version`)
- Maven 3.8+ (`mvn -version`)

## Build và chạy test

```bash
git clone -b core https://github.com/namkiet/project-add2numbers.git
cd project-add2numbers
mvn clean test
```

- Lịch sử từng bước tính được ghi qua SLF4J/Logback (in ra console khi chạy test).
- Báo cáo coverage (JaCoCo): `mybignumber-core/target/site/jacoco/index.html`.

## Dùng như thư viện

```bash
mvn clean install
```

```java
MyBigNumber n = new MyBigNumber();               // log từng bước qua SLF4J
String s = n.sum("1234", "897");                 // "2131"

List<String> steps = new ArrayList<>();
new MyBigNumber((i, msg) -> steps.add(msg)).sum("1234", "897"); // lấy lịch sử để hiển thị
```
