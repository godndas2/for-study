package com.example.member;

import com.example.team.Team;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static com.example.member.QMember.member;
import static com.example.team.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
public class MemberTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

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

    @Test
    @DisplayName("QueryDsl")
    public void startQuerydsl() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMember m = new QMember("m");

        Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1"))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    @DisplayName("집합")
    public void aggregation() throws Exception {
        List<Tuple> result = jpaQueryFactory
                .select(member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min())
                .from(member)
                .fetch();

        Tuple tuple = result.get(0);

        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);
    }

    @Test
    @DisplayName("group by")
    public void group() throws Exception {
        List<Tuple> result = jpaQueryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        Tuple A = result.get(0);
        Tuple B = result.get(1);

        assertThat(A.get(team.name)).isEqualTo("teamA");
        assertThat(A.get(member.age.avg())).isEqualTo(15);
        assertThat(B.get(team.name)).isEqualTo("teamB");
        assertThat(B.get(member.age.avg())).isEqualTo(35);
    }

    @Test
    @DisplayName("Team A에 소속된 모든 Member")
    public void join() {
        List<Member> result = jpaQueryFactory
                .selectFrom(member)
                .join(member.team, team) // team = QTeam
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    @Test
    @DisplayName("member의 이름이 team 이름과 같은 member 조회")
    public void theta_join() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        List<Member> result = jpaQueryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");
    }

}
