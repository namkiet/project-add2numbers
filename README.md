# Project Add 2 Numbers (MyBigNumber)

Dự án cài đặt thuật toán cộng hai số lớn (chuỗi số không giới hạn độ dài) và ứng dụng Web minh họa theo yêu cầu của **Remote Internship Program - Challenge for Software Developers** (Mã tài liệu: `Add2Num`, phiên bản `v1.8`).

---

## 1. Cấu trúc Dự án (Multi-module)

Dự án được tổ chức theo kiến trúc **Maven Multi-module**:

```text
project-add2numbers/
├── pom.xml                     # Maven root configuration
├── README.md                   # Tài liệu hướng dẫn chi tiết
├── mybignumber-core/           # TASK 1: Thư viện Core MyBigNumber (nhánh 'core', tag '0.0.1')
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/internship/mybignumber/
│       │   ├── MyBigNumber.java     # Class cài đặt thuật toán cộng theo cột
│       │   └── IStepListener.java   # Interface lắng nghe/ghi nhận lịch sử từng bước
│       └── test/java/com/internship/mybignumber/
│           └── MyBigNumberTest.java # 17 Unit test cases bao phủ 100% code coverage
└── mybignumber-web/            # TASK 2: Ứng dụng Web Spring Boot + Thymeleaf + Bootstrap
    ├── pom.xml
    └── src/main/
        ├── java/com/internship/mybignumber/web/
        │   ├── MyBigNumberWebApplication.java
        │   └── AdditionController.java
        └── resources/
            ├── application.properties
            └── templates/index.html # Giao diện nhập số và hiển thị tiến trình
```

---

## 2. Yêu cầu Môi trường
* **Java:** JDK 21 hoặc 17 LTS trở lên (`java -version`).
* **Maven:** Apache Maven 3.8+ (`mvn -version`).
* **Git:** Git client (`git --version`).

---

## 3. Hướng dẫn Biên dịch & Chạy Unit Test (Task 1)

Clone dự án về thư mục cục bộ theo đúng quy ước của tài liệu đặc tả:
```bash
# Ví dụ:
git clone https://github.com/youraccount/project-add2numbers.git
cd project-add2numbers
```

### Chạy Unit Test và xuất báo cáo Code Coverage (JaCoCo):
```bash
mvn clean test
```

* **Kết quả Unit Test:** Chạy toàn bộ 17 test cases (bao gồm: ca mẫu trong đề bài `1234 + 897 = 2131`, các ca số lớn hàng chục chữ số, phép cộng nhớ dồn liên tục qua nhiều chữ số `99999 + 1 = 100000`, độ dài lệch nhau, ca có số 0, ca xử lý dữ liệu trống/null phòng vệ).
* **Báo cáo Coverage (JaCoCo):** Sau khi chạy `mvn test`, mở file báo cáo tại:
  `mybignumber-core/target/site/jacoco/index.html` (Đạt **100% Instruction Coverage** và **100% Branch Coverage**).

---

## 4. Hướng dẫn Chạy Ứng dụng Web (Task 2)

Đóng gói và khởi chạy ứng dụng Spring Boot:
```bash
# 1. Đóng gói toàn bộ dự án
mvn clean package

# 2. Khởi chạy ứng dụng Web
java -jar mybignumber-web/target/mybignumber-web-0.0.1.jar
```
*(Hoặc chạy trực tiếp qua Maven plugin)*:
```bash
mvn spring-boot:run -pl mybignumber-web
```

Sau khi ứng dụng khởi động thành công:
1. Mở trình duyệt và truy cập: `http://localhost:8080/`
2. Nhập số thứ nhất (ví dụ: `1234`), nhập số thứ hai (ví dụ: `897`).
3. Bấm **"Thực hiện phép tính cộng"**.
4. Trang web sẽ hiển thị kết quả `2131` kèm theo danh sách chi tiết **Tiến trình thực hiện phép toán** từng bước.

---

## 5. Quy ước Nhánh & Phiên bản (Git)
* **Nhánh `core`**: Chứa toàn bộ mã nguồn của Task 1 (`mybignumber-core`, Unit Test, Logging).
* **Tag `0.0.1`**: Đánh dấu phiên bản hoàn thành đánh giá theo yêu cầu tài liệu `Add2Num`.
* **Nhánh `main`**: Chứa đầy đủ mã nguồn Task 1 và Task 2 (Web application tích hợp).
