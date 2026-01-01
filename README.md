# OldSpring4 - Java Web EE Project

## Tổng quan (Summary)
Đây là một project Java Web EE mẫu sử dụng các công nghệ tiêu chuẩn cho các hệ thống legacy nhưng được cập nhật để tương thích với Java 8:
- **Java**: 8
- **Framework**: Spring 4.3.30.RELEASE
- **UI**: JSF 2.2 + RichFaces 4.5.17.Final
- **ORM**: Hibernate 5.6.14.Final
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **API**: Hỗ trợ REST (Spring MVC) và SOAP (JAX-WS)

## Các tính năng cơ bản
1.  **CRUD User**: Quản lý thông tin user với giao diện RichFaces (`users.xhtml`).
2.  **REST API**: Cung cấp các endpoint tại `/api/users`.
3.  **SOAP Service**: Demo một service SOAP đơn giản tại `/UserSoapService`.
4.  **Spring Security**: Bảo mật API với Basic Auth (`user`/`user`).
5.  **EJB Integration**: Demo Stateless Session Bean inject vào JSF Bean.
6.  **Tooling**: Log4j logging, Lombok support.
7.  **JBoss EAP 7 Ready**: Cấu hình `jboss-deployment-structure.xml` để tránh xung đột thư viện.

## Hướng dẫn cài đặt và chạy project (Run Instructions)

### 1. Chuẩn bị Database và JBoss
- Dự án sử dụng JNDI DataSource `java:jboss/datasources/PostgresDS` để kết nối PostgreSQL.
- Bạn cần cài đặt JBoss EAP 7 (hoặc WildFly) và cấu hình Datasource.
- Xem chi tiết hướng dẫn tại: [JBOSS_SETUP.md](file:///d:/Git/OldSpring4/JBOSS_SETUP.md).

### 2. Build Project
Bạn cần cài đặt **JDK 8**. Nếu máy bạn đang để mặc định là Java 21, hãy mở terminal và set `JAVA_HOME` trỏ về JDK 8 trước khi build:

```powershell
# Ví dụ trên PowerShell
$env:JAVA_HOME = "C:\Path\To\Your\JDK8"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

# Sau đó build bằng Maven Wrapper (đã được tích hợp sẵn, không cần cài Maven)
.\mvnw.cmd clean install
```
Lệnh này sẽ tự động tải Maven và tạo ra file `OldSpring4.war` trong thư mục `target/`.

### 3. Deploy và Chạy
- Copy file `OldSpring4.war` vào thư mục `webapps/` của **Tomcat 8** hoặc **Tomcat 9**.
- Khởi động Tomcat.
- Truy cập vào ứng dụng qua trình duyệt:
  - **Giao diện quản lý User (JSF/RichFaces)**: `http://localhost:8080/OldSpring4/` (tự động chuyển hướng đến `users.xhtml`)
  - **REST API**: `http://localhost:8080/OldSpring4/api/users`
  - **SOAP WSDL**: `http://localhost:8080/OldSpring4/UserSoapService?wsdl` (Lưu ý: Cần cấu hình thêm JAX-WS server nếu container không hỗ trợ sẵn).

## Cấu trúc thư mục chính
- `src/main/java/com/example/config`: Cấu hình Spring (Java-based).
- `src/main/java/com/example/controller`: REST Controllers.
- `src/main/java/com/example/dao`: Tầng truy cập dữ liệu (Hibernate).
- `src/main/java/com/example/service`: Tầng nghiệp vụ (Service Layer).
- `src/main/java/com/example/view`: JSF Managed Beans.
- `src/main/webapp`: Các file giao diện `.xhtml` và cấu hình `web.xml`.
