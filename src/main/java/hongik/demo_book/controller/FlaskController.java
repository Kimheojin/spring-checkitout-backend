package hongik.demo_book.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hongik.demo_book.dto.RcdRequestDto;
import hongik.demo_book.dto.RcdReturnDto;
import hongik.demo_book.service.FlaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flask")
@RequiredArgsConstructor
public class FlaskController {

    private final FlaskService flaskService;

    //책 받으면 책 정보 저장 후 반환
    @PostMapping("/member/Recommendation")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<RcdReturnDto> flaks_recommend (
            @Valid @RequestBody RcdRequestDto rcdRequestDto
            ) throws JsonProcessingException {
        return ResponseEntity.ok(flaskService.BookRecommend(rcdRequestDto));
    }
}
