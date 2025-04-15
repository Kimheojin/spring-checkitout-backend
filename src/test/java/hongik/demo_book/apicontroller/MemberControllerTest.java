package hongik.demo_book.apicontroller;


import hongik.demo_book.config.WithMockBookUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("/api/hello 요청시 String 형태로 hello 반환")
    void test1() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"))
                .andDo(print());

    }

    @Test
    @WithMockBookUser()
    @DisplayName("기본 디폴트 USER 권한으로 회원 정보 조회")
    void testGetMemberInfoWithUserRole() throws Exception {
        mockMvc.perform(get("/api/member"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("huhiiijin1109@naver.com"))
                .andExpect(jsonPath("$.membername").value("HeoJinJin"))
                .andExpect(jsonPath("$.authorityDtoSet[0].authorityName")
                        .value("ROLE_USER"))
                .andExpect(status().isOk())
                .andDo(print());
    }

 /*   @Test
    @WithMockBookUser()
    @DisplayName("")*/


}
