package com.todo.modules.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

    ACCOUNT("ROLE_ACCOUNT", "일반 사용자");

    private final String key;
    private final String title;
}
