package hongik.demo_book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hongik.demo_book.config.WithMockBookUser;
import hongik.demo_book.dto.LoginDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthcontrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void beforeTest() {
        // 인증 객체 초기화
        SecurityContextHolder.clearContext();
    }

    @DisplayName("로그인 테스트")
    @WithMockBookUser()
    @Test
    void Test1() throws Exception {

        // given

        // 인증 컨텍스트 초기화 (로그아웃)
        SecurityContextHolder.clearContext();

        LoginDto loginDto = LoginDto.builder()
                .email("huhiiijin1109@naver.com")
                .password("champsWinnerChelsea")
                .build();


        // when + then

        // 인증객체 초기화 확인
        Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication());

        // http
        mockMvc.perform(post("/api/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk()) // 200
                .andDo(print());
    }


    @DisplayName("401 custom exception 테스트")
    @Test
    void Test2() throws Exception {

        // given
        LoginDto loginDto = LoginDto.builder()
                .email("hurjin1109@naver.com")
                .password("1234")
                .build();

        // when + then

        mockMvc.perform(post("/api/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isUnauthorized())
                .andDo(print());

    }
}
