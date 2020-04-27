package com.todo;

import com.todo.modules.account.Account;
import com.todo.modules.account.AccountRepository;
import com.todo.modules.account.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;

@SpringBootApplication
public class BootTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootTodoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AccountRepository accountRepository) {
        return args -> {
            accountRepository.save(Account.builder()
                    .email("test")
                    .password("{bcrypt}$2a$10$rES5T2c7SIT51HpTTs/nS.3Uh2sHoDIz6by9fphfzFgKFmjoYaatC") // 1234
                    .role(Role.ACCOUNT)
                    .build());

            accountRepository.save(Account.builder()
                    .email("test2")
                    .password("{bcrypt}$2a$10$rES5T2c7SIT51HpTTs/nS.3Uh2sHoDIz6by9fphfzFgKFmjoYaatC") // 1234
                    .role(Role.GUEST)
                    .build());
        };
    }
}
