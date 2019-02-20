package no.ntnu.sysdev.springtutorial;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @RequestMapping("/books/list")
    public String listBooks() {
        return "No books in the register";
    }
}
