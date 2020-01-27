package com.forstudy.querydsl.entity.academy;

import com.forstudy.querydsl.dto.StudentCount;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.forstudy.querydsl.entity.academy.QAcademy.academy;
import static com.forstudy.querydsl.entity.student.QStudent.student;
import static com.querydsl.core.types.ExpressionUtils.count;

@RequiredArgsConstructor
public class AcademyRepositoryCustomImpl implements AcademyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Academy> findByName(String name) {
        return queryFactory.selectFrom(academy)
                .where(academy.name.eq(name))
                .fetch();
    }

    @Override
    public List<StudentCount> findAllStudentCount() {
        return queryFactory.select(Projections.fields(StudentCount.class,
                academy.name.as("academyName"),
                ExpressionUtils.as(
                        JPAExpressions.select(count(student.id))
                        .from(student)
                        .where(student.academy.eq(academy)),
                        "studentCount")
                ))
                .from(academy)
                .fetch();
    }

    @Override
    public List<Academy> findAllByStudentId(long studentId) {
        return queryFactory.selectFrom(academy)
                .where(academy.id.in(
                        JPAExpressions.select(student.academy.id)
                        .from(student)
                        .where(student.id.eq(studentId))))
                        .fetch();
    }

}
