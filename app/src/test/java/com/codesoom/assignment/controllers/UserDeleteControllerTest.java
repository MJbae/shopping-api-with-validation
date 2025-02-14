package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.UserCrudService;
import com.codesoom.assignment.application.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("UserDeleteController")
class UserDeleteControllerTest {
    @MockBean
    private UserCrudService service;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final Long USER_ID = 1L;
    private final Long USER_ID_NOT_EXISTING = 10L;

    @Nested
    @DisplayName("delete 메소드는")
    class Describe_delete {
        @Nested
        @DisplayName("유효한 매개변수를 전달 받는다면")
        class Context_with_existing_user {
            @Test
            @DisplayName("HTTP Status Code 204 NO CONTENT 응답한다")
            void it_responds_with_204() throws Exception {
                mockMvc.perform(delete("/users/" + USER_ID))
                        .andExpect(status().isNoContent());
            }
        }

        @Nested
        @DisplayName("만약 존재하지 않는 ID를 매개변수로 전달 받는다면")
        class Context_without_existing_user {
            @BeforeEach
            void setUp() {
                willThrow(new UserNotFoundException(USER_ID_NOT_EXISTING))
                        .given(service).deleteBy(USER_ID_NOT_EXISTING);
            }

            @Test
            @DisplayName("HTTP Status Code 404 NOT FOUND 응답한다")
            void it_responds_with_404() throws Exception {
                mockMvc.perform(delete("/users/" + USER_ID_NOT_EXISTING))
                        .andExpect(status().isNotFound());
            }

        }
    }
}
