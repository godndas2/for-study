package org.bootvue.jwt.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HardCordService {

    private static List<User> users = new ArrayList<>();
    private static long idCounter = 0;

    static {
        users.add(new User(++idCounter, "halfdev", "Spring Boot and Angular"));
        users.add(new User(++idCounter, "halfdev", "Spring Boot and React"));
        users.add(new User(++idCounter, "halfdev", "Spring Boot and Spring Cloud"));
        users.add(new User(++idCounter, "halfdev", "Spring Boot and AI"));
    }

    public List<User> findAll() {
        return users;
    }

}
