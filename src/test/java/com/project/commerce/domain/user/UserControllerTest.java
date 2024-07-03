package com.project.commerce.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // 테스트 전에 필요한 데이터 추가
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        userRepository.save(user);
    }

    @Test
    void 회원정보_가져오기() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("ok"));
    }

    @Test
    void 회원정보_가져오기_회원이_없을_때() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 999L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("존재하지 않는 회원입니다.")); // 예외 발생 시 반환할 내용
    }

    @Test
    void 회원가입() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"1234\", \"password\": \"password\", \"email\": \"test@example.com\" }"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void 회원정보_업데이트() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"testuser\", \"password\": \"password123\", \"email\": \"test@example.com\" }"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void 회원삭제() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}