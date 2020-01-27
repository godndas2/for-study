package com.forstudy.querydsl;

import com.forstudy.querydsl.dto.StudentCount;
import com.forstudy.querydsl.entity.academy.Academy;
import com.forstudy.querydsl.entity.academy.AcademyRepository;
import com.forstudy.querydsl.entity.student.Student;
import com.forstudy.querydsl.entity.student.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;

/**
* @author halfdev
* @since 2020-01-27
* QueryDsl 은 From 절에 SubQuery 를 지원하지 않는다.
*/
@SpringBootTest
public class SubQueryTest {

    @Autowired private AcademyRepository academyRepository;
    @Autowired private StudentRepository studentRepository;

    @AfterEach
    public void tearDown() {
        academyRepository.deleteAll();
        studentRepository.deleteAll();
    }

    @Test
    public void SELECT_SUBQUERY() {
        // given
        String acaNameA = "nameA";
        Academy academy = Academy.builder()
                .name(acaNameA)
                .address("addrA")
                .build();

        academy.addStudent(Arrays.asList(
                new Student("AAA", 1),
                new Student("BBB", 2)
        ));

        String acaNameB = "nameB";
        Academy academy2 = Academy.builder()
                .address("addrB")
                .name(acaNameB)
                .build();

        academy2.addStudent(Arrays.asList(
                new Student("CCC", 3),
                new Student("DDD", 4),
                new Student("EEE", 5)
        ));

        academyRepository.saveAll(Arrays.asList(academy,academy2));

        // when
        List<StudentCount> allStudentCount = academyRepository.findAllStudentCount();

        // then
        assertThat(allStudentCount.get(0).getAcademyName(), is(acaNameA));
        assertThat(allStudentCount.get(1).getAcademyName(), is(acaNameB));
    }

    @Test
    public void WHERE_SUBQUERY() {
        // given
        String acaNameA = "nameA";
        Academy academy = Academy.builder()
                .name(acaNameA)
                .address("addrA")
                .build();

        academy.addStudent(Arrays.asList(
                new Student("AAA", 1),
                new Student("BBB", 2)
        ));

        String acaNameB = "nameB";
        Academy academy2 = Academy.builder()
                .address("addrB")
                .name(acaNameB)
                .build();

        academy2.addStudent(Arrays.asList(
                new Student("CCC", 3),
                new Student("DDD", 4),
                new Student("EEE", 5)
        ));

        academyRepository.saveAll(Arrays.asList(academy,academy2));

        // when
        List<Academy> allByStudentId = academyRepository.findAllByStudentId(3);

        // then
        assertThat(allByStudentId.size(), is(1));
        assertThat(allByStudentId.get(0).getName(), is(acaNameB));
    }



}
