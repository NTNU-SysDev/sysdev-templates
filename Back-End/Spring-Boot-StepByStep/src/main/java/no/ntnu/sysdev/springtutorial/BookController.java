package no.ntnu.sysdev.springtutorial;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @RequestMapping("/books/list")
    public List<Book> listBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("J K Rowling", "Harry Potter 1"));
        books.add(new Book("J K Rowling", "Harry Potter 2"));
        books.add(new Book("J K Rowling", "Harry Potter 3"));
        books.add(new Book("J K Rowling", "Harry Potter 4"));
        return books;
    }
}
