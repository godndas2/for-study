package org.rememberme.redis;

import org.rememberme.redis.model.User;
import org.rememberme.redis.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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
