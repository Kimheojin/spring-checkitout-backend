package hongik.demo_book.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import hongik.demo_book.Repository.BookRepository;
import hongik.demo_book.config.WithMockBookUser;
import hongik.demo_book.domain.CategoryName;
import hongik.demo_book.domain.Member;
import hongik.demo_book.dto.BookDto;
import hongik.demo_book.service.CustomUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomUserService customUserService;

    @BeforeEach
    void beforetest() {
        SecurityContextHolder.clearContext();
        bookRepository.deleteAll();
    }
    // 도서 저장

    @Test
    @WithMockBookUser()
    @DisplayName("책 저장")
    void test1() throws Exception {
        // given
        Member member = customUserService.GetCurrentMember();
        BookDto bookDto = BookDto.builder()
                .categoryName(CategoryName.valueOf("Favorite"))
                .isbn13("1234567890")
                .build();

        // when + then
        mockMvc.perform(post("/api/member/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk());





    }




    // 카테고리별 도서 목록 조회

    // 도서 삭제
}