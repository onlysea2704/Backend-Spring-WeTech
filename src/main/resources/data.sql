SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM my_courses;
DELETE FROM my_procedures;
DELETE FROM customer_searchs;
DELETE FROM procedures;
DELETE FROM courses;
DELETE FROM list_items;
DELETE FROM transactions;
DELETE FROM info_documents;
DELETE FROM document_sections;
DELETE FROM document_procedures;
DELETE FROM videos;
DELETE FROM sections;
DELETE FROM customers;
DELETE from users;


ALTER TABLE my_courses AUTO_INCREMENT = 1;
ALTER TABLE my_procedures AUTO_INCREMENT = 1;
ALTER TABLE customer_searchs AUTO_INCREMENT = 1;
ALTER TABLE procedures AUTO_INCREMENT = 1;
ALTER TABLE courses AUTO_INCREMENT = 1;
ALTER TABLE list_items AUTO_INCREMENT = 1;
ALTER TABLE transactions AUTO_INCREMENT = 1;
ALTER TABLE info_documents AUTO_INCREMENT = 1;
ALTER TABLE document_sections AUTO_INCREMENT = 1;
ALTER TABLE document_procedures AUTO_INCREMENT = 1;
ALTER TABLE videos AUTO_INCREMENT = 1;
ALTER TABLE sections AUTO_INCREMENT = 1;
ALTER TABLE customers AUTO_INCREMENT = 1;
ALTER TABLE users AUTO_INCREMENT = 1;

-- Bảng COURSES (Cập nhật lại type_course trong câu INSERT)
INSERT INTO courses (title, description, author, real_price, sale_price, type_course, link_image, intro_1, intro_2, number_register) VALUES
    ('Thành lập Công ty cho người mới', 'Hướng dẫn chi tiết thủ tục thành lập công ty TNHH và Cổ phần.', 'Nguyen Van A', 5000, 3990, 'Thành lập Công ty', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034167/course_1_vtf7uo.jpg', 'Học các bước cơ bản', 'Xây dựng điều lệ công ty', 250),
    ('Thay đổi đăng ký kinh doanh toàn tập', 'Nắm vững quy trình thay đổi giám đốc, địa chỉ, vốn điều lệ.', 'Tran Thi B', 4500, 3500, 'Đăng ký thay đổi', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034168/course_3_nirfm5.png', 'Các loại thay đổi', 'Hồ sơ cần chuẩn bị', 180),
    ('Thành lập Hộ kinh doanh cá thể 2025', 'Hướng dẫn từ A-Z để đăng ký và vận hành hộ kinh doanh.', 'Le Van C', 6000, 4500, 'Thành lập Hộ kinh doanh', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034170/course_2_occbug.jpg', 'Giới thiệu mô hình HKD', 'Quy trình đăng ký', 300),
    ('Quy trình giải thể Công ty đúng luật', 'Trọn bộ kiến thức về các bước giải thể doanh nghiệp an toàn.', 'Pham Thi D', 7000, 5500, 'Giải thể Công ty', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034167/course_1_vtf7uo.jpg', 'Điều kiện giải thể', 'Thủ tục với cơ quan thuế', 400),
    ('Giải thể Hộ kinh doanh nhanh gọn', 'Các bước cần làm để chấm dứt hoạt động của hộ kinh doanh.', 'Hoang Van E', 8000, 6000, 'Giải thể Hộ kinh doanh', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034168/course_3_nirfm5.png', 'Khi nào cần giải thể', 'Nộp hồ sơ và trả dấu', 550),
    ('Sáp nhập Tỉnh và các thủ tục liên quan', 'Tìm hiểu quy trình và pháp lý về sáp nhập đơn vị hành chính cấp Tỉnh.', 'Vu Thi F', 5500, 4000, 'Sáp nhập Tỉnh', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034170/course_2_occbug.jpg', 'Giới thiệu về sáp nhập', 'Các bước thực hiện', 220),
    ('Hướng dẫn cập nhật thông tin lên CCCD gắn chip', 'Toàn bộ quy trình đổi từ CMND sang CCCD và cập nhật thông tin doanh nghiệp.', 'Do Van G', 4000, 3000, 'Cập nhật lên CCCD', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034167/course_1_vtf7uo.jpg', 'Lợi ích của CCCD', 'Cập nhật cho người đại diện', 350),
    ('Thành lập công ty Cổ phần cho Startup', 'Tối ưu hóa cấu trúc vốn và điều lệ cho công ty khởi nghiệp.', 'Trinh Van H', 6500, 5000, 'Thành lập Công ty', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034168/course_3_nirfm5.png', 'Chọn loại hình doanh nghiệp', 'Huy động vốn cổ phần', 280),
    ('Đăng ký thay đổi thành viên/cổ đông', 'Hướng dẫn thủ tục chuyển nhượng vốn góp, cổ phần.', 'Mai Thi I', 3500, 2500, 'Đăng ký thay đổi', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034170/course_2_occbug.jpg', 'Chuyển nhượng vốn TNHH', 'Chuyển nhượng cổ phần', 150),
    ('Thành lập Hộ kinh doanh có điều kiện', 'Các thủ tục cần thiết để đăng ký những ngành nghề kinh doanh có điều kiện.', 'Bui Van K', 5000, 4000, 'Thành lập Hộ kinh doanh', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034167/course_1_vtf7uo.jpg', 'Ngành nghề có điều kiện', 'Xin giấy phép con', 200),
    ('Giải thể công ty và nghĩa vụ tài chính', 'Xử lý các vấn đề về thuế, bảo hiểm xã hội khi giải thể.', 'Dang Thi L', 9000, 7500, 'Giải thể Công ty', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034168/course_3_nirfm5.png', 'Quyết toán thuế', 'Chốt sổ BHXH', 450),
    ('Sáp nhập và những điều cần biết', 'Phân tích các khía cạnh pháp lý và tài chính của việc sáp nhập.', 'Ngo Van M', 5500, 4200, 'Sáp nhập Tỉnh', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034170/course_2_occbug.jpg', 'Hợp đồng sáp nhập', 'Định giá doanh nghiệp', 320),
    ('Cập nhật CCCD cho toàn bộ nhân viên công ty', 'Hướng dẫn doanh nghiệp thực hiện cập nhật thông tin cho người lao động.', 'Ly Thi N', 4000, 3500, 'Cập nhật lên CCCD', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034167/course_1_vtf7uo.jpg', 'Nghĩa vụ của doanh nghiệp', 'Tổ chức cập nhật tập trung', 600),
    ('Thành lập công ty TNHH một thành viên', 'Hướng dẫn chi tiết cho cá nhân muốn thành lập công ty.', 'Duong Van P', 6000, 5000, 'Thành lập Công ty', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034168/course_3_nirfm5.png', 'Ưu và nhược điểm', 'Hồ sơ chi tiết', 700),
    ('Đăng ký thay đổi địa chỉ khác quận/tỉnh', 'Quy trình và thủ tục khi di dời trụ sở chính sang địa bàn khác.', 'Ho Thi Q', 3000, 2000, 'Đăng ký thay đổi', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034170/course_2_occbug.jpg', 'Thủ tục với chi cục thuế', 'Thay đổi con dấu và hóa đơn', 120),
    ('Giải thể chi nhánh, văn phòng đại diện', 'Các bước chấm dứt hoạt động của đơn vị phụ thuộc.', 'Phan Van R', 7500, 6000, 'Giải thể Công ty', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034167/course_1_vtf7uo.jpg', 'Hồ sơ giải thể chi nhánh', 'Các lưu ý quan trọng', 260),
    ('Thành lập Hộ kinh doanh online', 'Đăng ký và tối ưu thuế cho mô hình kinh doanh trên mạng xã hội, sàn TMĐT.', 'Chau Thi S', 4500, 3800, 'Thành lập Hộ kinh doanh', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034168/course_3_nirfm5.png', 'Đăng ký thuế', 'Sử dụng hóa đơn điện tử', 480),
    ('Giải thể Hộ kinh doanh và các vấn đề thuế', 'Hướng dẫn quyết toán và đóng mã số thuế hộ kinh doanh.', 'Vo Van T', 3800, 3000, 'Giải thể Hộ kinh doanh', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034170/course_2_occbug.jpg', 'Kê khai lần cuối', 'Thủ tục với cơ quan thuế', 90),
    ('Đăng ký thay đổi ngành nghề kinh doanh', 'Hướng dẫn bổ sung, rút bớt ngành nghề đăng ký kinh doanh.', 'Ha Thi U', 10000, 8500, 'Đăng ký thay đổi', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034167/course_1_vtf7uo.jpg', 'Mã ngành cấp 4', 'Các ngành nghề cấm', 800),
    ('Thành lập công ty có vốn đầu tư nước ngoài', 'Quy trình xin giấy phép đầu tư và thành lập doanh nghiệp FDI.', 'Nguyen Kim V', 8500, 7000, 'Thành lập Công ty', 'https://res.cloudinary.com/dggpj05f2/image/upload/v1759034168/course_3_nirfm5.png', 'Giấy chứng nhận ĐKĐT', 'Thủ tục sau cấp phép', 330);

-- Bảng SECTIONS
-- Sections for Course 1: IELTS
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Kỹ năng Listening và Reading', 1);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Phân tích đề và viết bài Writing', 1);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Chiến thuật trả lời Speaking', 1);
-- Sections for Course 2: CCNA
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Các khái niệm mạng cơ bản', 2);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Địa chỉ IP và chia mạng con (Subnetting)', 2);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Định tuyến và chuyển mạch (Routing & Switching)', 2);
-- Sections for Course 3: Python
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Giới thiệu và Cài đặt', 3);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Biến, Kiểu dữ liệu và Toán tử', 3);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Cấu trúc điều khiển và Vòng lặp', 3);
-- Sections for Course 4: JavaScript
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Tổng quan về ES6', 4);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Arrow Functions và Promises', 4);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Async/Await và Modules', 4);
-- Sections for Course 5: Node.js
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Môi trường Node.js và NPM', 5);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Xây dựng máy chủ web với Express', 5);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Kết nối cơ sở dữ liệu MongoDB', 5);
-- Sections for Course 6: UI/UX
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Nền tảng về UI và UX', 6);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Wireframing và Prototyping', 6);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Thực hành với Figma', 6);
-- Sections for Course 7: Digital Marketing
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Tối ưu hóa công cụ tìm kiếm (SEO)', 7);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Quảng cáo trả phí (PPC)', 7);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Email Marketing và Automation', 7);
-- Sections for Course 8: Agile & Scrum
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Nguyên lý cốt lõi của Agile', 8);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Vai trò và sự kiện trong Scrum', 8);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Quản lý Backlog và Sprint', 8);
-- Sections for Course 9: SQL
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Truy vấn dữ liệu với SELECT', 9);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Kết hợp bảng với JOIN', 9);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Hàm tổng hợp và gom nhóm', 9);
-- Sections for Course 10: Flutter
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Ngôn ngữ Dart cơ bản', 10);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Xây dựng Widgets và Layout', 10);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Quản lý trạng thái (State Management)', 10);
-- Sections for Course 11: Photography
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Hiểu về máy ảnh và ống kính', 11);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Ánh sáng trong nhiếp ảnh', 11);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Chỉnh sửa ảnh hậu kỳ với Lightroom', 11);
-- Sections for Course 12: Accounting
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Hạch toán các nghiệp vụ cơ bản', 12);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Kế toán thuế', 12);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Lập và phân tích báo cáo tài chính', 12);
-- Sections for Course 13: Machine Learning
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Giới thiệu về Học Máy', 13);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Hồi quy tuyến tính và Logistic', 13);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Cây quyết định và Random Forest', 13);
-- Sections for Course 14: Illustrator
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Giao diện và công cụ cơ bản', 14);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Thao tác với màu sắc và Gradient', 14);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Vẽ và thiết kế nhân vật', 14);
-- Sections for Course 15: English
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Chào hỏi và giới thiệu bản thân', 15);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Viết Email và báo cáo chuyên nghiệp', 15);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Thuyết trình và họp hành', 15);
-- Sections for Course 16: Investment
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Tổng quan thị trường chứng khoán', 16);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Đọc biểu đồ nến và chỉ báo kỹ thuật', 16);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Xây dựng danh mục đầu tư', 16);
-- Sections for Course 17: Yoga
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Khởi động và làm nóng cơ thể', 17);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Chuỗi bài tập Chào Mặt Trời', 17);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Thư giãn và thiền định cuối buổi', 17);
-- Sections for Course 18: DevOps
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Văn hóa và nguyên tắc DevOps', 18);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Tích hợp liên tục (CI) với Git', 18);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Triển khai liên tục (CD) lên server', 18);
-- Sections for Course 19: Content Creation
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Xác định đối tượng và kênh truyền thông', 19);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Kỹ năng viết content thu hút', 19);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Phân tích và đo lường hiệu quả', 19);
-- Sections for Course 20: Cooking
INSERT INTO sections (name, course_id) VALUES ('Chương 1: Sơ chế và bảo quản nguyên liệu', 20);
INSERT INTO sections (name, course_id) VALUES ('Chương 2: Các món khai vị đặc sắc', 20);
INSERT INTO sections (name, course_id) VALUES ('Chương 3: Các món chính và tráng miệng', 20);



-- Bảng VIDEOS (cho các section từ ID 4 đến 63)
-- Videos for Python Course (Sections 4-6)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Giới thiệu và Cài đặt Python', 4, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Biến, Kiểu dữ liệu và Toán tử', 5, 1004);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Cấu trúc điều khiển và Vòng lặp', 6, 708);
-- Videos for JavaScript Course (Sections 7-9)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Tổng quan về ES6', 7, 1004);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Arrow Functions và Promises', 8, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Async/Await và Modules', 9, 1004);
-- Videos for Node.js Course (Sections 10-12)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Môi trường Node.js và NPM', 10, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Xây dựng máy chủ web với Express', 11, 1004);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Kết nối cơ sở dữ liệu MongoDB', 12, 708);
-- Videos for UI/UX Course (Sections 13-15)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Nền tảng về UI và UX', 13, 1004);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Wireframing và Prototyping', 14, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Thực hành với Figma', 15, 1004);
-- Videos for Digital Marketing Course (Sections 16-18)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Tối ưu hóa công cụ tìm kiếm (SEO)', 16, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Quảng cáo trả phí (PPC)', 17, 1004);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Email Marketing và Automation', 18, 708);
-- Videos for Agile & Scrum Course (Sections 19-21)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Nguyên lý cốt lõi của Agile', 19, 1004);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Vai trò và sự kiện trong Scrum', 20, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Quản lý Backlog và Sprint', 21, 1004);
-- Videos for SQL Course (Sections 22-24)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Truy vấn dữ liệu với SELECT', 22, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Kết hợp bảng với JOIN', 23, 1004);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Hàm tổng hợp và gom nhóm', 24, 708);
-- Videos for Flutter Course (Sections 25-27)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Ngôn ngữ Dart cơ bản', 25, 1004);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Xây dựng Widgets và Layout', 26, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Quản lý trạng thái (State Management)', 27, 994);
-- Videos for Photography Course (Sections 28-30)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Hiểu về máy ảnh và ống kính', 28, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Ánh sáng trong nhiếp ảnh', 29, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Chỉnh sửa ảnh hậu kỳ với Lightroom', 30, 708);
-- Videos for Accounting Course (Sections 31-33)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Hạch toán các nghiệp vụ cơ bản', 31, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Kế toán thuế', 32, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Lập và phân tích báo cáo tài chính', 33, 994);
-- Videos for Machine Learning Course (Sections 34-36)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Giới thiệu về Học Máy', 34, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Hồi quy tuyến tính và Logistic', 35, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Cây quyết định và Random Forest', 36, 708);
-- Videos for Illustrator Course (Sections 37-39)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Giao diện và công cụ cơ bản', 37, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Thao tác với màu sắc và Gradient', 38, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Vẽ và thiết kế nhân vật', 39, 994);
-- Videos for English Course (Sections 40-42)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Chào hỏi và giới thiệu bản thân', 40, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Viết Email và báo cáo chuyên nghiệp', 41, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Thuyết trình và họp hành', 42, 708);
-- Videos for Investment Course (Sections 43-45)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Tổng quan thị trường chứng khoán', 43, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Đọc biểu đồ nến và chỉ báo kỹ thuật', 44, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Xây dựng danh mục đầu tư', 45, 994);
-- Videos for Yoga Course (Sections 46-48)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Khởi động và làm nóng cơ thể', 46, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Chuỗi bài tập Chào Mặt Trời', 47, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Thư giãn và thiền định cuối buổi', 48, 708);
-- Videos for DevOps Course (Sections 49-51)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Văn hóa và nguyên tắc DevOps', 49, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Tích hợp liên tục (CI) với Git', 50, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Triển khai liên tục (CD) lên server', 51, 994);
-- Videos for Content Creation Course (Sections 52-54)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Xác định đối tượng và kênh truyền thông', 52, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Kỹ năng viết content thu hút', 53, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Phân tích và đo lường hiệu quả', 54, 708);
-- Videos for Cooking Course (Sections 55-57)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Sơ chế và bảo quản nguyên liệu', 55, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Các món khai vị đặc sắc', 56, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Các món chính và tráng miệng', 57, 708);
-- Videos for IELTS Course (Sections 58-60)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Kỹ năng Listening và Reading', 58, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Phân tích đề và viết bài Writing', 59, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Chiến thuật trả lời Speaking', 60, 708);
-- Videos for CCNA Course (Sections 1-3)
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Các khái niệm mạng cơ bản', 1, 708);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/v1757863410/20250914_222304videoplayback.mp4.mp4', 'Video: Địa chỉ IP và chia mạng con', 2, 994);
INSERT INTO videos (link, description, section_id, duration) VALUES ('https://res.cloudinary.com/dggpj05f2/video/upload/videoplayback_3_dwg7kr.mp4', 'Video: Định tuyến và chuyển mạch', 3, 708);


-- Bảng DOCUMENT_SECTIONS (cho các section từ ID 4 đến 63)
-- Docs for CCNA Course (Sections 1-3)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: So sánh mô hình OSI và TCP/IP', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 1);
INSERT INTO document_sections (name, link, section_id) VALUES ('Bài tập: Chia mạng con', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 2);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các lệnh cấu hình Cisco IOS', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 3);
-- Docs for Python Course (Sections 4-6)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Cài đặt môi trường Python', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 4);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các kiểu dữ liệu cơ bản', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 5);
INSERT INTO document_sections (name, link, section_id) VALUES ('Bài tập: Vòng lặp Python', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 6);
-- Docs for JavaScript Course (Sections 7-9)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Tổng hợp tính năng ES6', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 7);
INSERT INTO document_sections (name, link, section_id) VALUES ('Ví dụ: Sử dụng Promises', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 8);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: So sánh Async/Await và Promise', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 9);
-- Docs for Node.js Course (Sections 10-12)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các lệnh NPM phổ biến', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 10);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Middleware trong Express', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 11);
INSERT INTO document_sections (name, link, section_id) VALUES ('Hướng dẫn: Cài đặt MongoDB', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 12);
-- Docs for UI/UX Course (Sections 13-15)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: 10 nguyên tắc vàng trong UX', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 13);
INSERT INTO document_sections (name, link, section_id) VALUES ('Checklist: Các bước tạo Wireframe', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 14);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Phím tắt Figma', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 15);
-- Docs for Digital Marketing Course (Sections 16-18)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Nghiên cứu từ khóa SEO', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 16);
INSERT INTO document_sections (name, link, section_id) VALUES ('Template: Kế hoạch quảng cáo Google Ads', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 17);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các công cụ Email Marketing', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 18);
-- Docs for Agile & Scrum Course (Sections 19-21)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Tuyên ngôn Agile', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 19);
INSERT INTO document_sections (name, link, section_id) VALUES ('Sơ đồ: Luồng làm việc Scrum', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 20);
INSERT INTO document_sections (name, link, section_id) VALUES ('Template: User Story', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 21);
-- Docs for SQL Course (Sections 22-24)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các mệnh đề SELECT', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 22);
INSERT INTO document_sections (name, link, section_id) VALUES ('Sơ đồ: Các loại JOIN', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 23);
INSERT INTO document_sections (name, link, section_id) VALUES ('Bài tập: Gom nhóm dữ liệu', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 24);
-- Docs for Flutter Course (Sections 25-27)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Cú pháp cơ bản của Dart', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 25);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các Widgets phổ biến', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 26);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: So sánh các giải pháp State Management', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 27);
-- Docs for Photography Course (Sections 28-30)
INSERT INTO document_sections (name, link, section_id) VALUES ('Infographic: Các loại ống kính', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 28);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các kỹ thuật bố cục ánh sáng', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 29);
INSERT INTO document_sections (name, link, section_id) VALUES ('Preset: Bộ màu Lightroom miễn phí', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 30);
-- Docs for Accounting Course (Sections 31-33)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Hệ thống tài khoản kế toán', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 31);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các loại thuế doanh nghiệp', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 32);
INSERT INTO document_sections (name, link, section_id) VALUES ('Mẫu: Báo cáo tài chính', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 33);
-- Docs for Machine Learning Course (Sections 34-36)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Lịch sử Học Máy', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 34);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Hàm mất mát (Loss Function)', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 35);
INSERT INTO document_sections (name, link, section_id) VALUES ('Bài tập: Xây dựng Cây quyết định', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 36);
-- Docs for Illustrator Course (Sections 37-39)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Chức năng các công cụ', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 37);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Lý thuyết màu sắc', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 38);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu tham khảo: Thiết kế nhân vật', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 39);
-- Docs for English Course (Sections 40-42)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các mẫu câu giao tiếp', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 40);
INSERT INTO document_sections (name, link, section_id) VALUES ('Template: Mẫu Email tiếng Anh', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 41);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Từ vựng cho buổi họp', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 42);
-- Docs for Investment Course (Sections 43-45)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Thuật ngữ chứng khoán', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 43);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các mẫu hình nến phổ biến', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 44);
INSERT INTO document_sections (name, link, section_id) VALUES ('Bài tập: Phân bổ tài sản', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 45);
-- Docs for Yoga Course (Sections 46-48)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Lợi ích của khởi động', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 46);
INSERT INTO document_sections (name, link, section_id) VALUES ('Hình ảnh: 12 bước Chào Mặt Trời', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 47);
INSERT INTO document_sections (name, link, section_id) VALUES ('Hướng dẫn: Thiền cho người mới bắt đầu', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 48);
-- Docs for DevOps Course (Sections 49-51)
INSERT INTO document_sections (name, link, section_id) VALUES ('Sơ đồ: Vòng đời DevOps', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 49);
INSERT INTO document_sections (name, link, section_id) VALUES ('Template: Cấu hình .gitignore', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 50);
INSERT INTO document_sections (name, link, section_id) VALUES ('Script: Kịch bản triển khai tự động', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 51);
-- Docs for Content Creation Course (Sections 52-54)
INSERT INTO document_sections (name, link, section_id) VALUES ('Template: Chân dung khách hàng', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 52);
INSERT INTO document_sections (name, link, section_id) VALUES ('Checklist: Tối ưu bài viết chuẩn SEO', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 53);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các chỉ số đo lường (KPIs)', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 54);
-- Docs for Cooking Course (Sections 55-57)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Mẹo vặt nhà bếp', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 55);
INSERT INTO document_sections (name, link, section_id) VALUES ('Công thức: Các món gỏi và salad', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 56);
INSERT INTO document_sections (name, link, section_id) VALUES ('Công thức: Các món tráng miệng', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 57);
-- Docs for IELTS Course (Sections 58-60)
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các dạng câu hỏi Listening', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 58);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Từ vựng học thuật Writing Task 2', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 59);
INSERT INTO document_sections (name, link, section_id) VALUES ('Tài liệu: Các chủ đề Speaking thường gặp', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 60);



-- Bảng PROCEDURES (cha trước)
INSERT INTO procedures (title, description, type, real_price, sale_price, type_company, number_register) VALUES
    ('Thủ tục thành lập Công ty Cổ phần', 'Dịch vụ tư vấn và hoàn tất hồ sơ thành lập Công ty Cổ phần.', 'thanh_lap_cong_ty', 25000, 20000, 'cong_ty_co_phan', 150),
    ('Đăng ký thay đổi địa chỉ trụ sở chính', 'Thực hiện thủ tục thay đổi địa chỉ công ty TNHH 1 thành viên.', 'dang_ky_thay_doi', 8000, 6000, 'cong_ty_tnhh_mot_thanh_vien', 85),
    ('Thông báo thay đổi Giám đốc/Tổng Giám đốc', 'Soạn và nộp hồ sơ thông báo thay đổi người đại diện pháp luật.', 'thong_bao_thay_doi', 5000, 4000, 'cong_ty_tnhh_hai_thanh_vien_tro_len', 110),
    ('Thủ tục tạm ngừng kinh doanh 1 năm', 'Tư vấn và thực hiện thủ tục tạm ngừng kinh doanh cho Công ty Cổ phần.', 'tam_ngung_kinh_doanh', 10000, 8000, 'cong_ty_co_phan', 45),
    ('Thủ tục giải thể Công ty TNHH', 'Dịch vụ trọn gói giải thể doanh nghiệp TNHH 2 thành viên.', 'giai_the', 30000, 25000, 'cong_ty_tnhh_hai_thanh_vien_tro_len', 30),
    ('Thành lập Công ty TNHH một thành viên', 'Hồ sơ thành lập công ty TNHH 1 thành viên trọn gói, nhanh chóng.', 'thanh_lap_cong_ty', 18000, 15000, 'cong_ty_tnhh_mot_thanh_vien', 200),
    ('Đăng ký thay đổi vốn điều lệ', 'Tư vấn và thực hiện thủ tục tăng/giảm vốn điều lệ cho công ty cổ phần.', 'dang_ky_thay_doi', 9000, 7500, 'cong_ty_co_phan', 95),
    ('Thông báo thay đổi thông tin tài khoản ngân hàng', 'Thực hiện thông báo với cơ quan đăng ký kinh doanh về thay đổi tài khoản ngân hàng.', 'thong_bao_thay_doi', 4000, 3000, 'cong_ty_tnhh_mot_thanh_vien', 150),
    ('Thủ tục tạm ngừng kinh doanh 6 tháng', 'Dịch vụ tạm ngừng kinh doanh cho công ty TNHH 2 thành viên trở lên.', 'tam_ngung_kinh_doanh', 9000, 7000, 'cong_ty_tnhh_hai_thanh_vien_tro_len', 60),
    ('Thủ tục giải thể nhanh Công ty Cổ phần', 'Tư vấn các bước giải thể công ty cổ phần, đảm bảo đúng pháp luật.', 'giai_the', 40000, 35000, 'cong_ty_co_phan', 25),
    ('Thành lập Công ty TNHH hai thành viên trở lên', 'Dịch vụ thành lập công ty TNHH có từ 2 đến 50 thành viên.', 'thanh_lap_cong_ty', 22000, 18000, 'cong_ty_tnhh_hai_thanh_vien_tro_len', 180),
    ('Đăng ký thay đổi tên công ty', 'Thực hiện thủ tục đổi tên cho Công ty Cổ phần.', 'dang_ky_thay_doi', 7000, 5500, 'cong_ty_co_phan', 70),
    ('Thông báo thay đổi ngành, nghề kinh doanh', 'Bổ sung, thay đổi ngành nghề kinh doanh cho công ty TNHH.', 'thong_bao_thay_doi', 6000, 4500, 'cong_ty_tnhh_mot_thanh_vien', 130),
    ('Tạm ngừng kinh doanh và mở lại', 'Dịch vụ trọn gói tạm ngừng và đăng ký kinh doanh trở lại trước thời hạn.', 'tam_ngung_kinh_doanh', 12000, 10000, 'cong_ty_co_phan', 40),
    ('Giải thể chi nhánh công ty', 'Thủ tục chấm dứt hoạt động của chi nhánh, văn phòng đại diện.', 'giai_the', 20000, 16000, 'cong_ty_tnhh_hai_thanh_vien_tro_len', 22),
    ('Thủ tục thành lập doanh nghiệp có vốn đầu tư nước ngoài', 'Tư vấn thành lập công ty cổ phần có yếu tố nước ngoài.', 'thanh_lap_cong_ty', 50000, 45000, 'cong_ty_co_phan', 55),
    ('Đăng ký thay đổi thành viên góp vốn', 'Thủ tục thay đổi thành viên trong công ty TNHH 2 thành viên trở lên.', 'dang_ky_thay_doi', 10000, 8500, 'cong_ty_tnhh_hai_thanh_vien_tro_len', 65),
    ('Thông báo mẫu dấu mới của doanh nghiệp', 'Thủ tục thông báo về việc thay đổi con dấu công ty.', 'thong_bao_thay_doi', 4500, 3500, 'cong_ty_co_phan', 90),
    ('Thủ tục tạm ngừng kinh doanh cho TNHH 1 TV', 'Dịch vụ tạm ngừng kinh doanh nhanh gọn cho công ty TNHH một thành viên.', 'tam_ngung_kinh_doanh', 8500, 6500, 'cong_ty_tnhh_mot_thanh_vien', 50),
    ('Thủ tục giải thể công ty không phát sinh doanh thu', 'Tư vấn giải thể cho công ty TNHH một thành viên chưa có hoạt động.', 'giai_the', 25000, 22000, 'cong_ty_tnhh_mot_thanh_vien', 35);


-- Bảng DOCUMENT_PROCEDURE (tham chiếu procedure_id)
-- Procedure ID 1
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Đơn đề nghị đăng ký doanh nghiệp', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 1, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Điều lệ công ty', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 1, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 2
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo thay đổi nội dung đăng ký', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 2, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Quyết định của chủ sở hữu', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 2, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 3
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo thay đổi người đại diện', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 3, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Biên bản họp Hội đồng thành viên', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 3, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 4
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo về việc tạm ngừng kinh doanh', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 4, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Quyết định của Hội đồng quản trị', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 4, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 5
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo về việc giải thể doanh nghiệp', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 5, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Báo cáo thanh lý tài sản', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 5, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 6
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Giấy đề nghị thành lập TNHH 1TV', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 6, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Bản sao CMND/CCCD của chủ sở hữu', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 6, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 7
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo thay đổi vốn điều lệ', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 7, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Biên bản họp Đại hội đồng cổ đông', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 7, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 8
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo thay đổi thông tin ĐKKD (Phụ lục II-1)', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 8, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Giấy ủy quyền (nếu có)', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 8, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 9
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo tạm ngừng kinh doanh (mẫu)', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 9, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Biên bản họp HĐTV về việc tạm ngừng', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 9, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 10
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Quyết định giải thể của ĐHĐCĐ', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 10, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Danh sách chủ nợ và số nợ đã thanh toán', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 10, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 11
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Đơn đề nghị thành lập TNHH 2TV', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 11, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Danh sách thành viên', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 11, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 12
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo thay đổi tên công ty', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 12, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Quyết định ĐHĐCĐ về việc đổi tên', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 12, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 13
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo thay đổi ngành nghề kinh doanh', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 13, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Quyết định của chủ sở hữu về thay đổi', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 13, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 14
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo hoạt động trở lại', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 14, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Quyết định về việc hoạt động lại', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 14, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 15
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo chấm dứt hoạt động chi nhánh', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 15, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Biên bản họp về việc giải thể chi nhánh', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 15, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 16
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Giấy chứng nhận đăng ký đầu tư', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 16, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Hộ chiếu của nhà đầu tư nước ngoài', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 16, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 17
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo thay đổi thành viên', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 17, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Hợp đồng chuyển nhượng vốn góp', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 17, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 18
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo về việc sử dụng mẫu con dấu', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 18, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Mẫu con dấu mới', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 18, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 19
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Đơn xin tạm ngừng kinh doanh', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 19, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Quyết định của chủ sở hữu công ty', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 19, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');
-- Procedure ID 20
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit) VALUES
    ('Hồ sơ 1: Thông báo giải thể (mẫu)', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 20, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx'),
    ('Hồ sơ 2: Giấy xác nhận không nợ thuế', 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx', 20, 'https://res.cloudinary.com/dggpj05f2/raw/upload/v1757772261/%C4%90%E1%BA%A1o_%C4%90%E1%BB%A9c_H%C6%B0%E1%BB%9Bng_Nghi%E1%BB%87p_qp7gh5.docx');


-- Bảng INFO_DOCUMENTS (tham chiếu document_id)
INSERT INTO info_documents (question, map, note, document_id)
VALUES
    ('Cần giấy tờ gì để đăng ký?', 'Hà Nội', 'Thông tin hướng dẫn', 1),
    ('Phí đăng ký là bao nhiêu?', 'HCM', 'Chi tiết phí dịch vụ', 2);

-- Bảng CUSTOMERS
INSERT INTO customers (customer_id, name, phone, email) VALUES
(1, 'Trần Văn Mạnh', '0912345678', 'manh.tran@email.com'),
(2, 'Nguyễn Thị Hoa', '0987654321', 'hoa.nguyen@email.com'),
(3, 'Lê Minh Tuấn', '0905123456', 'tuan.le@email.com'),
(4, 'Phạm Thị Lan', '0934567890', 'lan.pham@email.com'),
(5, 'Hoàng Văn Long', '0978111222', 'long.hoang@email.com'),
(6, 'Vũ Thị Ánh', '0945333444', 'anh.vu@email.com'),
(7, 'Đặng Minh Đức', '0966777888', 'duc.dang@email.com'),
(8, 'Bùi Thị Thuý', '0923456789', 'thuy.bui@email.com'),
(9, 'Ngô Văn An', '0918765432', 'an.ngo@email.com'),
(10, 'Đỗ Thị Mai', '0988123123', 'mai.do@email.com'),
(11, 'Lý Văn Hùng', '0909456456', 'hung.ly@email.com'),
(12, 'Hồ Thị Kim', '0977890890', 'kim.ho@email.com'),
(13, 'Dương Văn Nam', '0913246810', 'nam.duong@email.com'),
(14, 'Mai Thị Bích', '0955667788', 'bich.mai@email.com'),
(15, 'Phan Văn Kiên', '0981765432', 'kien.phan@email.com');

-- Bảng CUSTOMER_SEARCH
# INSERT INTO customer_searchs (cccd, name, gender, dob)
# VALUES
#     ('012345678901', 'Nguyen Van E', 'Male', '1995-05-20'),
#     ('098765432109', 'Le Thi F', 'Female', '1998-09-15');

-- Bảng MY_COURSES (tham chiếu user_id, course_id)
INSERT INTO my_courses (user_id, course_id) VALUES
(1, 3), (1, 7), (2, 1), (2, 11), (3, 4), (3, 15), (4, 20), (4, 5), (5, 8), (5, 9),
(6, 12), (6, 2), (7, 18), (7, 6), (8, 13), (8, 14), (9, 1), (9, 21), (10, 10), (10, 19),
(11, 5), (11, 16), (12, 7), (12, 17), (13, 22), (13, 3), (14, 9), (14, 11), (15, 6), (15, 8);

-- Bảng MY_PROCEDURES (tham chiếu user_id, procedure_id)
INSERT INTO my_procedures (user_id, procedure_id) VALUES
(1, 1), (1, 6), (2, 2), (2, 7), (3, 3), (3, 11), (4, 4), (4, 12), (5, 5), (5, 15),
(6, 1), (6, 8), (7, 2), (7, 9), (8, 3), (8, 13), (9, 4), (9, 14), (10, 5), (10, 18),
(11, 1), (11, 10), (12, 2), (12, 16), (13, 3), (13, 17), (14, 4), (14, 19), (15, 5), (15, 20);

-- Bảng TRANSACTIONS (tham chiếu id_user)
INSERT INTO transactions (id_transaction, transfer_amount, transaction_start, transaction_date, status, id_user) VALUES
(1, 39900.0, '2025-09-01 10:00:00', '2025-09-01 10:01:00', 'SUCCESS', 1),
(2, 200000.0, '2025-09-02 11:30:00', '2025-09-02 11:31:00', 'SUCCESS', 1),
(3, 35000.0, '2025-09-03 14:00:00', '2025-09-03 14:01:00', 'SUCCESS', 2),
(4, 60000.0, '2025-09-04 09:00:00', '2025-09-04 09:01:00', 'SUCCESS', 3),
(5, 50000.0, '2025-09-05 16:20:00', '2025-09-05 16:21:00', 'SUCCESS', 4),
(6, 45000.0, '2025-09-06 08:00:00', '2025-09-06 08:01:00', 'PENDING', 5),
(7, 30000.0, '2025-09-07 12:00:00', '2025-09-07 12:01:00', 'SUCCESS', 6),
(8, 25000.0, '2025-09-08 15:00:00', '2025-09-08 15:01:00', 'SUCCESS', 7),
(9, 75000.0, '2025-09-09 18:00:00', '2025-09-09 18:01:00', 'FAILED', 8),
(10, 42000.0, '2025-09-10 10:45:00', '2025-09-10 10:46:00', 'SUCCESS', 9),
(11, 350000.0, '2025-09-11 11:00:00', '2025-09-11 11:01:00', 'SUCCESS', 10),
(12, 50000.0, '2025-09-12 13:00:00', '2025-09-12 13:01:00', 'SUCCESS', 11),
(13, 20000.0, '2025-09-13 19:00:00', '2025-09-13 19:01:00', 'SUCCESS', 12),
(14, 180000.0, '2025-10-01 09:15:00', '2025-10-01 09:16:00', 'SUCCESS', 13),
(15, 70000.0, '2025-10-02 10:00:00', '2025-10-02 10:01:00', 'SUCCESS', 14),
(16, 85000.0, '2025-10-03 14:30:00', '2025-10-03 14:31:00', 'PENDING', 15),
(17, 39900.0, '2025-10-04 17:00:00', '2025-10-04 17:01:00', 'SUCCESS', 1),
(18, 55000.0, '2025-10-05 20:00:00', '2025-10-05 20:01:00', 'SUCCESS', 2),
(19, 40000.0, '2025-10-06 21:00:00', '2025-10-06 21:01:00', 'SUCCESS', 3),
(20, 150000.0, '2025-10-07 11:00:00', '2025-10-07 11:01:00', 'SUCCESS', 4),
(21, 60000.0, '2025-10-08 12:00:00', '2025-10-08 12:01:00', 'SUCCESS', 5),
(22, 70000.0, '2025-10-09 13:00:00', '2025-10-09 13:01:00', 'SUCCESS', 6),
(23, 220000.0, '2025-10-10 16:00:00', '2025-10-10 16:01:00', 'FAILED', 7),
(24, 85000.0, '2025-10-11 15:10:00', '2025-10-11 15:11:00', 'SUCCESS', 8),
(25, 45000.0, '2025-10-12 18:00:00', '2025-10-12 18:01:00', 'SUCCESS', 9),
(26, 30000.0, '2025-10-13 09:00:00', '2025-10-13 09:01:00', 'SUCCESS', 10),
(27, 100000.0, '2025-10-14 10:00:00', '2025-10-14 10:01:00', 'SUCCESS', 11),
(28, 160000.0, '2025-10-15 11:00:00', '2025-10-15 11:01:00', 'SUCCESS', 12),
(29, 35000.0, '2025-10-16 14:00:00', '2025-10-16 14:01:00', 'SUCCESS', 13),
(30, 220000.0, '2025-10-17 15:00:00', '2025-10-17 15:01:00', 'SUCCESS', 14);

-- Bảng LIST_ITEMS (tham chiếu id_transaction, id_course, id_procedure)
INSERT INTO list_items (id_transaction, id_course, id_procedure, type_item) VALUES
(1, 3, NULL, 'COURSE'),
(2, NULL, 1, 'PROCEDURE'),
(3, 11, NULL, 'COURSE'),
(4, NULL, 2, 'PROCEDURE'),
(5, 15, NULL, 'COURSE'),
(6, NULL, 3, 'PROCEDURE'),
(7, 2, NULL, 'COURSE'),
(8, NULL, 5, 'PROCEDURE'),
(9, 13, NULL, 'COURSE'),
(10, NULL, 15, 'PROCEDURE'),
(11, NULL, 10, 'PROCEDURE'),
(12, 16, NULL, 'COURSE'),
(13, 17, NULL, 'COURSE'),
(14, NULL, 6, 'PROCEDURE'),
(15, NULL, 12, 'PROCEDURE'),
(16, 22, NULL, 'COURSE'),
(17, 7, NULL, 'COURSE'),
(18, NULL, 14, 'PROCEDURE'),
(19, 6, NULL, 'COURSE'),
(20, NULL, 4, 'PROCEDURE'),
(21, 18, NULL, 'COURSE'),
(22, NULL, 9, 'PROCEDURE'),
(23, NULL, 11, 'PROCEDURE'),
(24, 21, NULL, 'COURSE'),
(25, NULL, 13, 'PROCEDURE'),
(26, 9, NULL, 'COURSE'),
(27, NULL, 17, 'PROCEDURE'),
(28, NULL, 15, 'PROCEDURE'),
(29, 10, NULL, 'COURSE'),
(30, NULL, 20, 'PROCEDURE');