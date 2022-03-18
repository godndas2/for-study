package com.example.member.dto;

import lombok.Data;

@Data
public class MemberSearchCondition {
    // 회원명, 팀명, 나이 (ageGoe, ageLoe)
    private String username;
    private String teamName;
    private Integer ageGoe; // 값이 NULL 일 수 있으니 Integer
    private Integer ageLoe; // 값이 NULL 일 수 있으니 Integer
}
