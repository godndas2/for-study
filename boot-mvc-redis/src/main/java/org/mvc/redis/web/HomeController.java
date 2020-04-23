package org.mvc.redis.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("signin")
    public String login() {
        return "signin";
    }

    @GetMapping("main")
    public String main(Model model) {
        String id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("user", id);
        return "main";
    }

    @GetMapping("error")
    public String error() {
        return "error";
    }
}
