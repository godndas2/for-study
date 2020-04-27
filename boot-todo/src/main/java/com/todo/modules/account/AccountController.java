package com.todo.modules.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class AccountController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/main")
    public String main(Principal principal, Model model) {
        final String loggedInUserName = principal.getName();
        model.addAttribute("loggedInUserName", loggedInUserName);
        return "main";
    }

}
