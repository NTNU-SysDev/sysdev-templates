package no.ntnu.sysdev.springtutorial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping(value = "/app")
    public String indexPage() {
        return "app";
    }
}
