package com.forstudy.querydsl;

import com.forstudy.querydsl.entity.academy.Academy;
import com.forstudy.querydsl.entity.academy.AcademyRepository;
import com.forstudy.querydsl.entity.academy.AcademyRepositorySupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
public class BasicTest {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyRepositorySupport academyRepositorySupport;

    @AfterEach
    public void tearDown() throws Exception {
        academyRepository.deleteAllInBatch();
    }

    @Test
    public void queryDsl_Basic_Test() {
        String name = "halfdev";
        String email = "halfdev@test.mail";
        academyRepository.save(new Academy(name,email));

        List<Academy> findByName = academyRepository.findByName(name);

        assertThat(findByName.size(), is(1));
        assertThat(findByName.get(0).getAddress(), is(email));
    }
}


