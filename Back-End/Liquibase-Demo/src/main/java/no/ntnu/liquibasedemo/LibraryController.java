package no.ntnu.liquibasedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    private LibraryRepository repository;

    @RequestMapping(method = RequestMethod.GET, path = "/libraries/list")
    public List<Library> getAll() {
        return repository.findAll();
    }
}
