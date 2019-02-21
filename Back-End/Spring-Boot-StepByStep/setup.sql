DROP TABLE IF EXISTS `books`;

CREATE TABLE `books`
(
  `id` INT(11) NOT NULL PRIMARY KEY,
  `author` VARCHAR(255) NOT NULL,
  `title`  VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

INSERT INTO `books` (id, author, title)
VALUES (1, "J K Rowling", "Harry Potter 1"),
       (2, "J K Rowling", "Harry Potter 2"),
       (3, "J K Rowling", "Harry Potter 3"),
       (4, "Daniel Kahneman", "Thinking Fast and Slow")
;