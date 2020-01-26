package com.forstudy.querydsl.entity.academy;

import java.util.List;

/**
* @author halfdev
* @since 2020-01-26
* The most important part of the class name that corresponds to the fragment interface is the Impl postfix.
*/
public interface AcademyRepositoryCustom {
    List<Academy> findByName(String name);
}
