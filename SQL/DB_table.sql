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
	c_no int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    m_no int NOT NULL,
    attendance timestamp NOT NULL,
    leave_work timestamp NOT NULL,
    FOREIGN KEY(c_no) REFERENCES member(m_no)
);

CREATE TABLE board (
	b_no int auto_increment PRIMARY KEY,
    m_id varchar(20),
    b_title varchar(40)  NOT NULL,
    b_content varchar(2000) NOT NULL,
    b_date timestamp NOT NULL,
    b_view int DEFAULT 0,
    FOREIGN KEY(m_id) REFERENCES member(m_id)
);

/*부서 테이블 설정*/
ALTER TABLE dept AUTO_INCREMENT=1000;
INSERT INTO dept(d_name) VALUES('경영지원');
INSERT INTO dept(d_name) VALUES('인사');
INSERT INTO dept(d_name) VALUES('홍보마케팅');
INSERT INTO dept(d_name) VALUES('기획');
INSERT INTO dept(d_name) VALUES('디자인');
