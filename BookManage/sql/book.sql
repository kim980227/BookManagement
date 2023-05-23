DROP TABLE t_book;
-- auto-generated definition
CREATE TABLE t_book
(
    book_id        int AUTO_INCREMENT
        PRIMARY KEY,
    category_1     tinyint       DEFAULT 10 NOT NULL COMMENT '카테고리 ( 10: 소설, 20: 역사, 30: 자기개발, 40: 종교 ... )',
    category_2     tinyint       DEFAULT 11 NOT NULL COMMENT '카테고리 ( 11: 한국소설, 12: 서양소설  ... )',
    book_name      varchar(255) NULL COMMENT '책이름',
    summary        varchar(255) NULL COMMENT '한줄요약',
    author         varchar(255) NULL COMMENT '저자',
    publisher      varchar(255) NULL COMMENT '출판사',
    purchase_price decimal(10, 2) NULL COMMENT '매입가격',
    selling_price  decimal(10, 2) NULL COMMENT '판매가격',
    qty            int NULL COMMENT '수량',
    img            varchar(255) NULL COMMENT '표지',
    page           int NULL COMMENT '책 페이지',
    edition        int           DEFAULT 1 NULL COMMENT '에디션',
    discount_rate  decimal(5, 2) DEFAULT 0.00 NULL COMMENT '할인율',
    created_ts     timestamp     DEFAULT CURRENT_TIMESTAMP NULL COMMENT '생성일시 ',
    is_deleted     char          DEFAULT 'F' NULL COMMENT '삭제여부 T/F',
    deleted_ts     timestamp NULL COMMENT '삭제일자'
);

INSERT INTO t_book (category_1, category_2, book_name, summary, author, publisher, purchase_price, selling_price, qty, img, page, edition)
VALUES (10, 11, '한국소설 1', '한국소설 1의 요약', '한국소설 작가 1', '출판사 A', 20.00, 30.00, 50, 'book1.jpg', 200, 1),
       (10, 11, '한국소설 2', '한국소설 2의 요약', '한국소설 작가 2', '출판사 B', 15.00, 25.00, 30, 'book2.jpg', 150, 1),
       (10, 12, '서양소설 1', '서양소설 1의 요약', '서양소설 작가 1', '출판사 C', 18.00, 28.00, 40, 'book3.jpg', 180, 1),
       (20, 21, '한국역사 1', '한국역사 1의 요약', '한국역사 작가 1', '출판사 D', 25.00, 35.00, 60, 'book4.jpg', 250, 1),
       (20, 22, '세계역사 1', '세계역사 1의 요약', '세계역사 작가 1', '출판사 E', 30.00, 40.00, 70, 'book5.jpg', 300, 1),
       (30, 31, '자기개발 1', '자기개발 1의 요약', '자기개발 작가 1', '출판사 F', 20.00, 30.00, 50, 'book6.jpg', 200, 1),
       (30, 32, '자기개발 2', '자기개발 2의 요약', '자기개발 작가 2', '출판사 G', 15.00, 25.00, 30, 'book7.jpg', 150, 1),
       (40, 41, '종교서적 1', '종교서적 1의 요약', '종교서적 작가 1', '출판사 H', 18.00, 28.00, 40, 'book8.jpg', 180, 1),
       (40, 42, '종교서적 2', '종교서적 2의 요약', '종교서적 작가 2', '출판사 I', 25.00, 35.00, 60, 'book9.jpg', 250, 1),
       (40, 42, '종교서적 3', '종교서적 3의 요약', '종교서적 작가 3', '출판사 J', 30.00, 40.00, 70, 'book10.jpg', 300, 1);


SELECT *
FROM t_book;