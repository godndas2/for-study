package com.forstudy.oauth2.rest;

import com.forstudy.oauth2.entity.User;
import com.forstudy.oauth2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/user")
    public List<User> listUser() {
        return userService.findAll();
    }

    @PostMapping(value = "/user")
    public User create(@RequestBody @Valid User user) {
        return userService.save(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public String remove(@PathVariable(value = "id") Long id) {
        userService.remove(id);
        return "success";
    }

}
