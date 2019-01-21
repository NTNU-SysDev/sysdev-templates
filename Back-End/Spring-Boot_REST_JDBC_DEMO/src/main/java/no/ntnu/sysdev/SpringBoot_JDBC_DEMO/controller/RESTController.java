package no.ntnu.sysdev.SpringBoot_JDBC_DEMO.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.ntnu.sysdev.SpringBoot_JDBC_DEMO.Entity.User;
import no.ntnu.sysdev.SpringBoot_JDBC_DEMO.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RESTController {

    private UserService userService;
    private ObjectMapper objectMapper;


    /**
     * Constructor for class RESTController
     * <code>@Autowired</code> allows us to skip
     * configurations elsewhere of what to inject
     * and does this for us
     */
    @Autowired
    public RESTController(UserService userService) {
        this.userService = userService;
        this.objectMapper = new ObjectMapper();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<String> getUsers() {
        try {
            String listOfUsers = objectMapper.writeValueAsString(userService.getAllUsers());
            return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (userService.addUser(user)) {
            return new ResponseEntity<>("User Created!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not create user", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestParam(value = "email") String email) {
        if (userService.deleteUser(email)) {
            return new ResponseEntity<>("User deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

}
