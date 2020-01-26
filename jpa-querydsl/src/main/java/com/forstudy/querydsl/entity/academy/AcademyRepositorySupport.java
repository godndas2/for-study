package com.forstudy.querydsl.entity.academy;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.forstudy.querydsl.entity.academy.QAcademy.academy;

@Repository
public class AcademyRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public AcademyRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Academy.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    /**
    * @author halfdev
    * @since 2020-01-26
    * academy 를 import 하는 방법
     * maven compile -> QAcademy.java 생성
    */
    public List<Academy> findByName(String name) {
        return jpaQueryFactory.selectFrom(academy)
                .where(academy.name.eq(name))
                .fetch();
    }


}
