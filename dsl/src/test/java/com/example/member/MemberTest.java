package com.example.member;

import com.example.team.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@Commit
public class MemberTest {

    @Autowired
    EntityManager em;

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

    @AfterEach
    public void tearDown() {
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("Entity")
    public void testEntity() {
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        for (Member member : members) {
            System.out.println("members=" + member);
            System.out.println("-> member.team=" + member.getTeam());
        }
    }

    @Test
    @DisplayName("JPQL")
    public void jpql() {
        String members =
                "select m from Member m " +
                "where m.username = :username";

        Member findMember = em.createQuery(members, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }
}
