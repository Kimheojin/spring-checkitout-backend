package hongik.demo_book.member.controller;

import hongik.demo_book.member.entity.Member;
import hongik.demo_book.address.dto.AddressDto;
import hongik.demo_book.member.dto.MemberDto;
import hongik.demo_book.address.dto.AddressResponse;
import hongik.demo_book.member.service.CustomUserService;
import hongik.demo_book.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final CustomUserService customUserService;

    //서버 동작 확인용 (Token 없어도 동작)

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    //토큰 있을시 api/member 로 이동
    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/member");
    }

    //회원가입

    @PostMapping("/signup")
    public ResponseEntity<MemberDto> signup(
            @Valid @RequestBody MemberDto memberDto
    ) {
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    //USER권한이랑 ADMIN 모두 허용
    @GetMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberDto> getMyMemberInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities());
    }

    //ADMIN만 허용
    @GetMapping("/member/{memberEmail}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberDto> getMemberInfo(@PathVariable String memberEmail) {
        return ResponseEntity.ok(memberService.getMemberWithAuthorities(memberEmail));
    }





}
