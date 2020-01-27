package com.forstudy.querydsl.entity.academy;

import com.forstudy.querydsl.dto.StudentCount;
import com.forstudy.querydsl.entity.student.Student;

import java.util.List;

/**
* @author halfdev
* @since 2020-01-26
* The most important part of the class name that corresponds to the fragment interface is the Impl postfix.
*/
public interface AcademyRepositoryCustom {
    List<Academy> findByName(String name);
    List<StudentCount> findAllStudentCount();
    List<Academy> findAllByStudentId(long studentId);
}
