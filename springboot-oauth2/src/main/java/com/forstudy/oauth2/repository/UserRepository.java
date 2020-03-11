package com.forstudy.oauth2.repository;

import com.forstudy.oauth2.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
