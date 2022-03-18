package com.example.member.repository;

import com.example.member.dto.MemberSearchCondition;
import com.example.member.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
    Page<MemberTeamDto> searchPage(MemberSearchCondition condition, Pageable pageable);
}
