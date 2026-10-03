package org.example.thymeleaftemplate.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;

    @Controller
    static class TestExceptionController {
        @GetMapping("/test/not-found")
        public String throwNotFound() {
            throw new ResourceNotFoundException("Elemento de prueba no encontrado");
        }

        @GetMapping("/test/server-error")
        public String throwServerError() {
            throw new RuntimeException("Error inesperado de prueba");
        }
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new TestExceptionController())
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void shouldHandleResourceNotFoundException() throws Exception {
        mockMvc.perform(get("/test/not-found"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/404"))
                .andExpect(model().attribute("message", "Elemento de prueba no encontrado"));
    }

    @Test
    void shouldHandleGeneralException() throws Exception {
        mockMvc.perform(get("/test/server-error"))
                .andExpect(status().isInternalServerError())
                .andExpect(view().name("error/500"))
                .andExpect(model().attribute("message", "Ocurrió un error inesperado en el servidor."));
    }
}
