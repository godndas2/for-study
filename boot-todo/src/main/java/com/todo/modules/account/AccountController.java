package com.todo.modules.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AccountController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

}
