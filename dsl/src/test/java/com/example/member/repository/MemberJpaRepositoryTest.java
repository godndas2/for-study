package com.example.member.repository;

import com.example.member.Member;
import com.example.member.dto.MemberSearchCondition;
import com.example.member.dto.MemberTeamDto;
import com.example.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @BeforeEach
    public void init() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member memberTest1 = new Member("member1", 10, teamA);
        Member memberTest2 = new Member("member2", 20, teamA);
        Member memberTest3 = new Member("member3", 30, teamB);
        Member memberTest4 = new Member("member4", 40, teamB);
        em.persist(memberTest1);
        em.persist(memberTest2);
        em.persist(memberTest3);
        em.persist(memberTest4);
    }

    @Test
    public void test() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(member).isEqualTo(member);

        List<Member> resultA = memberJpaRepository.findAll();
        assertThat(resultA).containsExactly(member);

        List<Member> resultB = memberJpaRepository.findByUsername("member1");
        assertThat(resultB).containsExactly(member);
    }

    @Test
    public void searchTest() {
        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");

        List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition);
//        List<MemberTeamDto> result = memberJpaRepository.search(condition);
        assertThat(result).extracting("username").containsExactly("member4");
    }


}
