package com.forstudy.oauth2.web;

import com.forstudy.oauth2.model.entity.Role;
import com.forstudy.oauth2.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class GreetingControllerTests {

    @Autowired
    WebApplicationContext webApplicationContext;
//    @Autowired
//    private FilterChainProxy filterChainProxy;
    @InjectMocks
    GreetingController controller;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8")).build();

        User user = new User();
        user.setId(1);
        user.setName("Name");
        user.setPassword("1234");
        user.setLogin("login");

        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");
    }

    @Test
    public void greetingUnauthorized() throws Exception {
        mockMvc.perform(get("/greeting")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error", is("unauthorized")));
    }


}
