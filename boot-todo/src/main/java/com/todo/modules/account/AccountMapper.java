package com.todo.modules.account;

import com.todo.modules.account.dto.AccountDto;

import java.util.Objects;

public class AccountMapper {

    public static AccountDto entityToDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .password(account.getPassword())
                .role(account.getRole())
                .build();
    }

    public static Account dtoToEntity(AccountDto accountDto) {
        Account account = Account.builder()
                .email(accountDto.getEmail())
                .password(accountDto.getPassword())
                .role(accountDto.getRole())
                .build();

        if (Objects.nonNull(account.getId())) {
            account.setId(accountDto.getId());
        }

        return account;
    }
}
