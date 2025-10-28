package hongik.demo_book.book.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hongik.demo_book.member.entity.Member;
import hongik.demo_book.book.dto.RcdRequestDto;
import hongik.demo_book.book.dto.RcdReturnDto;
import hongik.demo_book.member.service.CustomUserService;
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
    private final CustomUserService customUserService;


    //책 받으면 책 정보 저장 후 반환
    @PostMapping("/member/Recommendation")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<RcdReturnDto> flaks_recommend (
            @Valid @RequestBody RcdRequestDto rcdRequestDto
            ) throws JsonProcessingException {
        Member member = customUserService.GetCurrentMember();
        return ResponseEntity.ok(flaskService.BookRecommend(rcdRequestDto, member));
    }
}
