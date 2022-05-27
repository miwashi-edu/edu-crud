DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS
(
    ID INT NOT NULL AUTO_INCREMENT,
    AUTHOR_ID INT,
    TITLE VARCHAR(45) NOT NULL,
    ISBN VARCHAR(45),
    PUBLISHED VARCHAR(45),
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS
(
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(45) NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS
(
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS LIBRARY_CARDS;
CREATE TABLE LIBRARY_CARDS
(
    ID INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS USER_BOOK;
CREATE TABLE USER_BOOK
(
    ID INT NOT NULL AUTO_INCREMENT,
    USER_ID NOT NULL,
    BOOK_ID NOT NULL,
    OUT DATE,
    RETURNED DATE,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS GRADES;
CREATE TABLE GRADES
(
    ID INT,
    NAME VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO GRADES(ID, NAME)
VALUES
(1, "GRADE 1"),
(2, "GRADE 2"),
(3, "GRADE 3"),
(4, "GRADE 4"),
(5, "GRADE 5");
