package hongik.demo_book.apicontroller;

import hongik.demo_book.dto.AddressDto;
import hongik.demo_book.dto.MemberDto;
import hongik.demo_book.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //서버 동작 확인용 (Token 없어도 동작)
    @Operation(summary = "서버 동작 확인 기능", description = "토큰 없어도 작동")
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    //토큰 있을시 api/member 로 이동
    @Operation(summary = "서버 동작 확인 기능2", description = "토큰 포함한 상태로 보내면 권환 확인해서 보내줌")
    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/member");
    }

    //회원가입
    @Operation(summary = "회원가입", description = "멤버이름, 이메일, 비밀번호")
    @PostMapping("/signup")
    public ResponseEntity<MemberDto> signup(
            @Valid @RequestBody MemberDto memberDto
    ) {
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    //USER권한이랑 ADMIN 모두 허용
    //권한 정보 이용해서 내용 반환하는듯
    //email도 반환
    @Operation(summary = "권환 확인용", description = "토큰 보내면 확인")
    @GetMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberDto> getMyMemberInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities());
    }

    //ADMIN만 허용
    @Operation(summary = "권환 확인용", description = "url에 member이름 보내면 확인(admin) 토큰만")
    @GetMapping("/member/{membername}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberDto> getMemberInfo(@PathVariable String membername) {
        return ResponseEntity.ok(memberService.getMemberWithAuthorities(membername));
    }



    //주소 저장(구현 완료)
    //주소 변경
    @Operation(summary = "주소 저장", description = "주소 3개 보내면 저장")
    @PostMapping("/member/addressSave")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<AddressDto> saveMemberAddress(
            @Valid @RequestBody AddressDto addressdto
    ) {
        return ResponseEntity.ok(memberService.AddressSave(addressdto));
    }


    //주소 삭제
    @Operation(summary = "주소 삭제", description = "토큰만 보내도 삭제")
    @DeleteMapping("/member/addressDelete")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<AddressDto> deleteMemberAddress(HttpServletRequest request) {
        memberService.AddressDelete();
        return ResponseEntity.noContent().build();
    }

    //저장된 주소 반환
    @Operation(summary = "주소 반환", description = "토큰 보내면 주소 반환")
    @GetMapping("/member/addressReturn")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<AddressDto> returnMemberAddress(HttpServletRequest request){
        return ResponseEntity.ok(memberService.AddressReturn());
    }

}
