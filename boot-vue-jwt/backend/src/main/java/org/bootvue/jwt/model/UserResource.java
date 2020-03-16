package org.bootvue.jwt.model;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping("/instructors/{username}/users")
    public List<User> getAllUsers(@PathVariable String username) {
        return userService.findAll();
    }

}
