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


INSERT INTO to_do (id, title, content, priority, status, unix_time)
VALUES 
(1, '백엔드 로직 작성', '일정 관리 앱 백엔드 로직 작성', 'HIGH', 'IN_PROGRESS', UNIX_TIMESTAMP('2025-02-14 00:00:00')),
(2, '백엔드 QA', '백엔드 로직 QA 진행', 'MEDIUM', 'NOT_STARTED', UNIX_TIMESTAMP('2025-02-16 00:00:00'));


INSERT INTO to_do_sub (title, content, status, todo_id)
VALUES 
('Mock API 작업', '프론트와 연결을 위한 Mock API 작성', 'COMPLETED', 1),
('Service 작업', '내부 로직 작성', 'IN_PROGRESS', 1),

('Mock API 테스트', 'Mock API 테스트 진행', 'IN_PROGRESS', 2),
('Service 테스트', '내부 로직 테스트 진행', 'NOT_STARTED', 2);

