package com.forstudy.querydsl.entity.academy;

import com.forstudy.querydsl.dto.StudentCount;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

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

    // where 에 null 이 Parameter 로 올 경우 조건문에서 제외
    // , 는 and 역할을 한다.
    @Override
    public List<Academy> findDynamicQuery(String name, String address, String phoneNumber) {
        return queryFactory.selectFrom(academy)
                .where(eqName(name),
                        eqAddress(address),
                        eqPhoneNumber(phoneNumber))
                .fetch();
    }

    // Non-Service
    private BooleanExpression eqName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        return academy.name.eq(name);
    }

    private BooleanExpression eqAddress(String address) {
        if (StringUtils.isEmpty(address)) {
            return null;
        }

        return academy.address.eq(address);
    }

    private BooleanExpression eqPhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            return null;
        }

        return academy.phoneNumber.eq(phoneNumber);
    }

}
