package org.mvc.redis.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("signin")
    public String login() {
        return "signin";
    }
}
