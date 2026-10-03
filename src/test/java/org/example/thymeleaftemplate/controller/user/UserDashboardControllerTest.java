package org.example.thymeleaftemplate.controller.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class UserDashboardControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void shouldRedirectFromRootToDashboard() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/dashboard"));
    }

    @Test
    void shouldReturnUserDashboardView() throws Exception {
        mockMvc.perform(get("/user/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/dashboard"))
                .andExpect(content().string(containsString("Bienvenido a tu panel")));
    }
}
