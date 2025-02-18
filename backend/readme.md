## ERD
https://www.erdcloud.com/d/vzM3YkgnXukW5NuN4

## SQL 파일
```
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

CREATE SCHEMA test;
USE test;

DROP TABLE IF EXISTS to_do;
DROP TABLE IF EXISTS to_do_sub;

CREATE TABLE to_do
(
    id        BIGINT       PRIMARY KEY AUTO_INCREMENT,
    title     VARCHAR(50) NOT NULL,
    content   VARCHAR(1000) NOT NULL,
    priority  ENUM('HIGH', 'MEDIUM', 'LOW', 'NONE') NOT NULL,
    status    ENUM('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED') NOT NULL,
    unix_time BIGINT       NOT NULL
);

CREATE TABLE to_do_sub
(
    id      BIGINT       PRIMARY KEY AUTO_INCREMENT,
    title   VARCHAR(50) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    status  ENUM('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED') NOT NULL,
    todo_id BIGINT       NOT NULL,
    CONSTRAINT fk_todo FOREIGN KEY (todo_id) REFERENCES to_do(id) ON DELETE NO ACTION
);

CREATE INDEX idx_unix_time ON to_do (unix_time);


ALTER TABLE to_do CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE to_do_sub CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```