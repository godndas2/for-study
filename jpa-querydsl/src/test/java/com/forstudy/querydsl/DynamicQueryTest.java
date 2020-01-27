package com.forstudy.querydsl;

import com.forstudy.querydsl.entity.academy.Academy;
import com.forstudy.querydsl.entity.academy.AcademyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;

@SpringBootTest
public class DynamicQueryTest {

    @Autowired private AcademyRepository academyRepository;

    @AfterEach
    public void tearDown() {
        academyRepository.deleteAll();
    }

    @Test
    public void DynamicQuery_TEST() {
        // given
        String name = "testName";

        academyRepository.saveAll(Arrays.asList(
                new Academy(name, name, ""),
                new Academy("name is null", "", "")));

        // when
        List<Academy> dynamicQuery = academyRepository.findDynamicQuery(name, "", "");

        // then
        assertThat(dynamicQuery.size(), is(1));
        assertThat(dynamicQuery.get(0).getAddress(), is(name));
    }

    @Test
    public void DynamicQuery_Param_ALL() {
        // given
        String targetName = "name";
        String targetAddress = "address";
        String targetPhoneNumber = "phoneNumber";
        academyRepository.saveAll(Arrays.asList(
                new Academy(targetName, targetAddress, targetPhoneNumber),
                new Academy("", "not target", "")
        ));

        // when
        List<Academy> academies = academyRepository.findDynamicQuery(targetName, targetAddress, targetPhoneNumber);

        // then
        assertThat(academies.size(), is(1));
        assertThat(academies.get(0).getAddress(), is(targetAddress));
    }
}
