package no.ntnu.sysdev.springtutorial;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller class. It will serve a single endpoint: /hello , which returns a static string
 */
@RestController
public class HelloController {

    /**
     * Handles requests to backend endpoint /hello
     * @return A static message
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Welcome to the Spring world!";
    }
}
