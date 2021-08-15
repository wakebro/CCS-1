SHOW DATABASES;

CREATE DATABASE ccs default character set utf8;

USE ccs;

show tables;

CREATE TABLE members (
	id VARCHAR(20) NOT NULL,
    pw VARCHAR(20) NOT NULL,
    name VARCHAR(10) NOT NULL,
    depart VARCHAR(7) NOT NULL,
	sNum INT PRIMARY KEY AUTO_INCREMENT,
    pNum INT NOT NULL,
	email VARCHAR(30) NOT NULL
    );

CREATE INDEX board_ibfk_1 ON members (id);

CREATE TABLE board (
	textNum INT(9) PRIMARY KEY AUTO_INCREMENT,
    writer_id VARCHAR(20) NOT NULL,
    title VARCHAR(40) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    date TIMESTAMP NOT NULL,
    view INT(9),
    FOREIGN KEY (writer_id) REFERENCES members(id)
    );
    
SELECT * FROM members;
SELECT * FROM board;
