package hongik.demo_book.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hongik.demo_book.book.service.BookService;
import hongik.demo_book.cateogory.service.CategoryName;
import hongik.demo_book.member.entity.Member;
import hongik.demo_book.book.dto.BookDto;
import hongik.demo_book.book.dto.RcdRequestDto;
import hongik.demo_book.book.dto.RcdReturnDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlaskService {

    private final BookService bookService;
    private final ObjectMapper objectMapper;

    @Transactional
    public RcdReturnDto BookRecommend(RcdRequestDto rcdRequestDto, Member member) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String param = objectMapper.writeValueAsString(rcdRequestDto);

        // Flask 서버에 요청을 보냄
        HttpEntity<String> entity = new HttpEntity<>(param, headers);
        ResponseEntity<RcdReturnDto> response = restTemplate.exchange(
                "http://203.249.75.56:42018/recommend",
                HttpMethod.POST,
                entity,
                RcdReturnDto.class
        );
        //결과값 dto로 저장
        RcdReturnDto result = response.getBody();

        List<Long> similarBooks = result.getSimilarBooks();

        //저장부분 구현하기
        similarBooks.forEach(isbn13 -> {
            // bookDto 초기화
            BookDto bookDto = BookDto.builder()
                    .isbn13(String.valueOf(isbn13))
                    .categoryName(CategoryName.valueOf("Recommendation"))
                    .build();
            // 책 저장
            bookService.BookSave(bookDto, member);
        });

        return result;
    }
}
