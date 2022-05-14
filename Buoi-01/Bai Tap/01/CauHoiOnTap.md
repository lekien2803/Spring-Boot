*1. Trong quá trình tạo dự án Spring Boot chúng ta phải khai báo những tham số sau đây : groupID, artifactID. Ý nghĩa các tham số này là gì?*
**Trả lời**: 
- groupID là tên công ty hoặc nhóm tạo ra dự án đó.
- artifactID là tên dự án.

*2. Tại sao phải đảo ngược tên miền trong* ``<groupId>vn.techmaster</groupId>``*?*
**Trả lời:**
- chương trình chạy từ package lớn tới nhỏ.


*3. SpringBoot có 2 cơ chế để quản lý thư viện. Hãy kể tên chúng?*
**Trả lời:**
- Maven và Gradle

*4. File pom.xml có tác dụng gì?*
**Trả lời:**
- File pom.xml là nơi khai báo tất cả những gì liên quan đến dự án được cấu hình qua maven, như khai báo các dependency, version của dự án, tên dự án, repossitory...

*5. Trong file pom.xml có các thẻ dependency. Ý nghĩa của chúng là gì?*
**Trả lời:**
- một dạng kho lưu trữ mà dự án cần để biên dịch, xây dựng, chạy chương trình.

*6. Ý nghĩa của ``@Controller`` là gì?*
**Trả lời:**
- Thể hiện rằng class đó có vai trò là bộ điều khiển.

*7. Ý nghĩa của ``@RequestMapping`` là gì? Nó có những tham số gì ngoài value?*
**Trả lời:**
- Annotation này ánh xạ các HTTP request tới các phương thức xử lý của MVC và REST controller.
- Ngoài value còn có tham số method.

*8. Ý nghĩa của @RequestBody khi đặt trong hàm hứng request để làm gì?*
**Trả lời:**
- được dùng để ánh xạ HttpRequest body sang một domain object tự động.

*9. Hãy trả lời khi nào thì dùng @PathVariable và khi nào nên dùng @RequestParam*
**Trả lời:**
- ``@RequestParam`` được dùng để trích xuất dữ liệu từ request query. ``@PathVariable`` thì được dùng để trích xuất dữ liệu từ URL path.

*10. Thứ tự các thành phần đường dẫn @PathVariable có thể hoán đổi được không?*
**Trả lời:**
- có thể hoán đổi.

*11. @GetMapping khác gì so với @PostMapping?*
**Trả lời:**
- @GetMapping tổng hợp hoạt động cho phương thức get của @RequestMapping.
- @PostMapping tổng hợp hoạt động cho phương thức post của @RequestMapping.

*14. Cổng mặc định ứng dụng SpringBoot là 8080. Hãy google cách để thay đổi cổng lắng nghe mặc định*
**Trả lời:**
- có thể thay đổi bằng cách điều chỉnh giá trị server.port trong application.properties. ví dụ: ``server.port=8081``