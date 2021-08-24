CREATE DATABASE ccs;

USE ccs;

CREATE TABLE dept (
	dept_no int AUTO_INCREMENT,
    d_name varchar(20) NOT NULL,
    CONSTRAINT PK PRIMARY KEY(dept_no)
);

CREATE TABLE member (
	m_name varchar(20) NOT NULL,
    m_no int NOT NULL AUTO_INCREMENT,
    m_id varchar(20) NOT NULL,
    m_pw varchar(20) NOT NULL,
    dept_no int NOT NULL,
    m_phone varchar(15),
    m_email varchar(30) NOT NULL,
    FOREIGN KEY(dept_no) REFERENCES dept(dept_no),
    PRIMARY KEY(m_no, m_id)
);
CREATE INDEX idx_member_id ON member(m_id);
/* 복합키에서 특정 부분키만 참조하기 위해 생성*/

CREATE TABLE commute (
    m_no int NOT NULL PRIMARY KEY,
    m_name VARCHAR(5) NOT NULL,
    clock_out_time VARCHAR(20),
     clock_in_time VARCHAR(20),
    FOREIGN KEY(m_no) REFERENCES member(m_no)
);
INSERT INTO commute (m_name, clock_out_time, clock_in_time) VALUES('기하', '20210823193018', '20210823191906');

ALTER TABLE commute DROP FOREIGN KEY commute_ibfk_1;
ALTER TABLE commute ADD CONSTRAINT FK_1 FOREIGN KEY(m_no) REFERENCES member(m_no);
ALTER TABLE commute MODIFY attendance VARCHAR(20);
ALTER TABLE commute CHANGE leave_work clock_out_time VARCHAR(20);
ALTER TABLE commute CHANGE attendance clock_in_time VARCHAR(20);
RENAME TABLE commute TO commute_bak;
DELETE FROM commute;

CREATE TABLE board (
	b_no int auto_increment PRIMARY KEY,
    m_id varchar(20),
    b_title varchar(40)  NOT NULL,
    b_content varchar(2000) NOT NULL,
    b_date timestamp NOT NULL,
    b_view int DEFAULT 0,
    FOREIGN KEY(m_id) REFERENCES member(m_id)
);

CREATE TABLE approval (
	a_no INT AUTO_INCREMENT PRIMARY KEY,
    a_status VARCHAR(20)  DEFAULT '대기',
    a_category VARCHAR(20) NOT NULL,
    a_reason VARCHAR(2000),
    d_name VARCHAR(20) NOT NULL,
    m_no int NOT NULL,
    m_name VARCHAR(20) NOT NULL,
    a_start TIMESTAMP NOT NULL,
    a_end TIMESTAMP NOT NULL,
    a_head VARCHAR(20) DEFAULT '',
    FOREIGN KEY approval_FK (m_no) REFERENCES member(m_no)
);

CREATE TABLE a_category (
	cate_no INT AUTO_INCREMENT PRIMARY KEY,
    cate_name VARCHAR(20)
);

/*결재 내용 양식*/
ALTER TABLE dept AUTO_INCREMENT=1000;
INSERT INTO a_category(cate_name) VALUES('휴가');
INSERT INTO a_category(cate_name) VALUES('반차');
INSERT INTO a_category(cate_name) VALUES('월차');
INSERT INTO a_category(cate_name) VALUES('연차');

/*부서 테이블 설정*/
ALTER TABLE dept AUTO_INCREMENT=1000;
INSERT INTO dept(d_name) VALUES('경영지원');
INSERT INTO dept(d_name) VALUES('인사');
INSERT INTO dept(d_name) VALUES('홍보마케팅');
INSERT INTO dept(d_name) VALUES('기획');
INSERT INTO dept(d_name) VALUES('디자인');
INSERT INTO dept(d_name) VALUES('IT');
INSERT INTO dept(d_name) VALUES('경영기획');
UPDATE dept SET d_name='관리자' WHERE dept_no=1000;


ALTER TABLE commute MODIFY COLUMN leave_work TIMESTAMP NULL;
INSERT INTO commute(m_no, attendance) VALUES(1,now());