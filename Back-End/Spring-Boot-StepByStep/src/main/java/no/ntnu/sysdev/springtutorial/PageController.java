package no.ntnu.sysdev.springtutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class PageController {
    private final BookRepository repository;

    @Autowired
    public PageController(BookRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/app")
    public String indexPage(Model model) {
        model.addAttribute("books", repository.findAll());
        return "app";
    }

    @RequestMapping(value = "/app/addBook", method = RequestMethod.GET)
    public String addBookPage() {
        return "addBook";
    }

    @RequestMapping(value = "/app/addBook", method = RequestMethod.POST)
    public String handleFormSubmit(@Valid Book book, BindingResult result, Model model) {
        String templateName = "addBook";
        if (result.hasErrors()) {
            // Error happened
            FieldError fe = result.getFieldError();
            if (fe != null) {
                String fieldName = fe.getField();
                model.addAttribute("errorMessage", "Something wrong with field: " + fieldName);
            }
        } else {
            // Form submission fine
            String error = repository.add(book);
            if (error != null) {
                model.addAttribute("errorMessage", "Could not add book to DB: " + error);
            } else {
                // Book added successfully
                templateName = "bookAdded";
            }
        }
        return templateName;
    }
}
