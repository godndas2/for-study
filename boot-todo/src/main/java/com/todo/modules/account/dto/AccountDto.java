package com.todo.modules.account.dto;

import com.todo.modules.account.Account;
import com.todo.modules.account.Role;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
@Getter
@Builder
public class AccountDto {

    private Long id;

    @NotNull
    private String email;

    private String password;

    private Role role;

    public Account toEntity() {
        return Account.builder()
                .email(email)
                .password(password)
                .role(Role.ACCOUNT)
                .build();
    }
}
