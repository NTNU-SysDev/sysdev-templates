package no.ntnu.liquibasedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookRepository repository;

    @RequestMapping(method = RequestMethod.GET, path = "/books/list")
    public List<Book> getAll() {
        return repository.findAll();
    }
}
