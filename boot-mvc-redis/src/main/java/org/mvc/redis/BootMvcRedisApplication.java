package org.mvc.redis;

import org.mvc.redis.model.User;
import org.mvc.redis.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Optional;

@SpringBootApplication
public class BootMvcRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMvcRedisApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            userRepository.save(User.builder()
                    .id("testId")
                    .password("{bcrypt}$2a$10$rES5T2c7SIT51HpTTs/nS.3Uh2sHoDIz6by9fphfzFgKFmjoYaatC") // 1234
                    .name("testName")
                    .build());
        };
    }
}
