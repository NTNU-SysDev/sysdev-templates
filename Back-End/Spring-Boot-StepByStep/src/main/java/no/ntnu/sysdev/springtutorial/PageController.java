package no.ntnu.sysdev.springtutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
