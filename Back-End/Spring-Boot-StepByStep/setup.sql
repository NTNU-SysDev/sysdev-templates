DROP TABLE IF EXISTS `books`;

CREATE TABLE `books`
(
  `author` VARCHAR(255) NOT NULL,
  `title`  VARCHAR(255) NOT NULL,
  PRIMARY KEY (`title`)
) ENGINE = InnoDB;

INSERT INTO `books` (author, title)
VALUES ("J K Rowling", "Harry Potter 1"),
       ("J K Rowling", "Harry Potter 2"),
       ("J K Rowling", "Harry Potter 3"),
       ("Daniel Kahneman", "Thinking Fast and Slow")
;