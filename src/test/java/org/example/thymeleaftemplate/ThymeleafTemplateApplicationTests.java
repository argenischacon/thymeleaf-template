package org.example.thymeleaftemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ThymeleafTemplateApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void shouldServeWebJarCssWithoutVersion() throws Exception {
        mockMvc.perform(get("/webjars/bootstrap/css/bootstrap.min.css"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldServeWebJarJsWithoutVersion() throws Exception {
        mockMvc.perform(get("/webjars/bootstrap/js/bootstrap.bundle.min.js"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRenderHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
