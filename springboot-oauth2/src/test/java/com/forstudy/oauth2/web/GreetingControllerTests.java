package com.forstudy.oauth2.web;

import com.forstudy.oauth2.model.entity.Role;
import com.forstudy.oauth2.model.entity.Roles;
import com.forstudy.oauth2.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    public List<Role> users;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .addFilter(new CharacterEncodingFilter("UTF-8")).build();

        users = new ArrayList<>();

        User user = new User();
        user.setId(1);
        user.setName("NameA");
        user.setPassword("1234");
        user.setLogin("login");
        user.setRoles(Roles.ROLE_USER);

//        Role role = new Role();
//        role.setId(1);
//        role.setName("ROLE_USER");
//        role.setUsers(Collections.singleton(user));
//        users.add(role);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void greetingAuthorized() throws Exception {
        mockMvc.perform(get("/greeting")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.error", is("unauthorized")));
    }


}
