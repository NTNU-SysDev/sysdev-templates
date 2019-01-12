package no.ntnu.sysdev.SpringBoot_Demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.ntnu.sysdev.SpringBoot_Demo.entity.User;
import no.ntnu.sysdev.SpringBoot_Demo.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This REST Controller handles
 * all incoming requests from other clients
 * with the given domain specified in
 * CrossOrigin.
 */
@RestController
@CrossOrigin
public class RESTController {

    private final UserService userService;
    private ObjectMapper objectMapper;

    /**
     * Constructor for class RESTController
     * <code>@Autowired</code> allows us to skip
     * configurations elsewhere of what to inject
     * and does this for us
     *
     * @param userService the user service
     */
    @Autowired
    public RESTController(UserService userService) {
        this.userService = userService;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Returns a ResponseEntity containing
     * all the users that are registered
     * in the database as a string
     *
     * @return a ResponseEntity containing
     *         all the users that are registered
     *         in the database as a string
     */
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

    /**
     * Creates a user with the specified information given
     * in the http request and adds him/her to the database.
     *
     * @param user user to create
     * @return a ResponseEntity with http status code 200 if successful
     *         or 400 if not
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.createUser(user)) {
            return new ResponseEntity<>("User created", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not create user", HttpStatus.BAD_REQUEST);
        }
     }

    /**
     * Deletes a user from the database specified by his/her
     * email address given in the http request
     *
     * @param email Email address of the user to delete
     * @return httpStatus 200 OK if user was successfully deleted
     * or 400 Bad Request if not
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestParam(value = "email") String email) {
        if (userService.deleteUser(email)) {
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

}
