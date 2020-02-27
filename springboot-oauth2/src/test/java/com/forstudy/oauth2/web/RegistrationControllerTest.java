package com.forstudy.oauth2.web;

import com.forstudy.oauth2.model.User;
import com.forstudy.oauth2.model.VerificationToken;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    RegistrationController controller;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).addFilter(new CharacterEncodingFilter("UTF-8")).build();
    }

    @Test
    public void getFormGet() throws Exception {
        mockMvc.perform(get("/email-verification"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("verification-form"))
                .andDo(print());
    }

    // TODO
    @Test@Ignore
    public void postFormGet() {

    }

    // TODO
    @Test@Ignore
    public void getVerifyEmail() throws Exception {
        final String code = UUID.randomUUID().toString();
        final VerificationToken token = createToken();


        mockMvc.perform(get("/verify-email")
                .param("code", code))
                .andExpect(status().isOk())
                .andDo(print());
    }

    // TODO
    private VerificationToken createToken() {
        return null;
    }
}
