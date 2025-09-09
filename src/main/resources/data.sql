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


-- Bảng COURSES
INSERT INTO courses (title, description, author, real_price, sale_price, type_course, link_image, intro_1, intro_2, number_register)
VALUES
    ('Java Spring Boot', 'Khóa học Spring Boot từ cơ bản đến nâng cao', 'Nguyen Van A', 200.0, 150.0, 'IT', 'img/java.png', 'Giới thiệu 1', 'Giới thiệu 2', 100),
    ('ReactJS Pro', 'Khóa học ReactJS nâng cao', 'Tran Van B', 180.0, 120.0, 'IT', 'img/react.png', 'Intro 1', 'Intro 2', 80);

-- Bảng SECTIONS (tham chiếu course_id)
INSERT INTO sections (name, course_id)
VALUES
    ('Giới thiệu Spring', 1),
    ('REST API Spring Boot', 1),
    ('React Cơ bản', 2);

-- Bảng VIDEOS (tham chiếu section_id)
INSERT INTO videos (link, description, section_id)
VALUES
    ('video1.mp4', 'Giới thiệu Spring Boot', 1),
    ('video2.mp4', 'Tạo REST API', 2),
    ('video3.mp4', 'React component cơ bản', 3);

-- Bảng DOCUMENT_SECTION (tham chiếu section_id)
INSERT INTO document_sections (name, link, section_id)
VALUES
    ('Tài liệu Spring Boot', 'doc/spring.pdf', 1),
    ('Tài liệu REST API', 'doc/rest.pdf', 2),
    ('Tài liệu ReactJS', 'doc/react.pdf', 3);

-- Bảng PROCEDURES (cha trước)
INSERT INTO procedures (title, description, type, real_price, sale_price, type_company, number_register)
VALUES
    ('Đăng ký kinh doanh', 'Thủ tục đăng ký kinh doanh cơ bản', 'tlct',500.0, 400.0, 'Công ty TNHH', 50),
    ('Cấp phép xây dựng', 'Thủ tục xin giấy phép xây dựng', 'dktd', 800.0, 700.0, 'Công ty Xây dựng', 30);

-- Bảng DOCUMENT_PROCEDURE (tham chiếu procedure_id)
INSERT INTO document_procedures (name, link_material_raw, procedure_id, link_material_edit)
VALUES
    ('Hồ sơ đăng ký A', 'raw/fileA.docx', 1, 'edit/fileA.pdf'),
    ('Hồ sơ đăng ký B', 'raw/fileB.docx', 2, 'edit/fileB.pdf');

-- Bảng INFO_DOCUMENTS (tham chiếu document_id)
INSERT INTO info_documents (question, map, note, document_id)
VALUES
    ('Cần giấy tờ gì để đăng ký?', 'Hà Nội', 'Thông tin hướng dẫn', 1),
    ('Phí đăng ký là bao nhiêu?', 'HCM', 'Chi tiết phí dịch vụ', 2);

-- Bảng CUSTOMERS
INSERT INTO customers (name, phone, email)
VALUES
    ('Nguyen Van C', '0901234567', 'cust1@gmail.com'),
    ('Tran Van D', '0987654321', 'cust2@gmail.com');

-- Bảng CUSTOMER_SEARCH
# INSERT INTO customer_searchs (cccd, name, gender, dob)
# VALUES
#     ('012345678901', 'Nguyen Van E', 'Male', '1995-05-20'),
#     ('098765432109', 'Le Thi F', 'Female', '1998-09-15');

-- Bảng MY_COURSES (tham chiếu user_id, course_id)
INSERT INTO my_courses (user_id, course_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1);

-- Bảng MY_PROCEDURES (tham chiếu user_id, procedure_id)
INSERT INTO my_procedures (user_id, procedure_id)
VALUES
    (1, 1),
    (2, 2);

-- Bảng TRANSACTIONS (tham chiếu id_user)
INSERT INTO transactions (transfer_amount, transaction_start, transaction_date, status, id_user)
VALUES
    (150.0, '2025-09-01 10:00:00', '2025-09-01 10:05:00', 'SUCCESS', 1),
    (120.0, '2025-09-02 11:00:00', '2025-09-02 11:05:00', 'PENDING', 2);

-- Bảng LIST_ITEMS (tham chiếu id_transaction, id_course, id_procedure)
INSERT INTO list_items (id_transaction, id_course, id_procedure, type_item)
VALUES
    (1, 1, NULL, 'COURSE'),
    (1, 2, NULL, 'COURSE'),
    (2, NULL, 1, 'PROCEDURE');