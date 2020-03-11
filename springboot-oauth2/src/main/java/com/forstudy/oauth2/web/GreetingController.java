package com.forstudy.oauth2.web;

import com.forstudy.oauth2.model.Greeting;
import com.forstudy.oauth2.model.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private static final AtomicLong counter = new AtomicLong();

    @GetMapping("/")
    public String index() {
        return "INDEX PAGE";
    }

    @GetMapping("/greeting")
    public Greeting getGreeting(@AuthenticationPrincipal User user) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, user.getName()));
    }
}
