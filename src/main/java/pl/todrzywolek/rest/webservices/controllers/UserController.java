package pl.todrzywolek.rest.webservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.todrzywolek.rest.webservices.models.User;
import pl.todrzywolek.rest.webservices.models.UserNotFoundException;
import pl.todrzywolek.rest.webservices.services.UserDaoService;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userService;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable("id") int id) {
        User user = userService.findOne(id);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User with id "+ id + " not found.");
        }
    }

    // CREATED
    // INPUT - DETAILS OF THE USER
    // OUTPUT - CREATED STATUS AND RETURN THE CREATED URI

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);

        // created status and send newly created user id
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
