package hongik.demo_book.address.controller;


import hongik.demo_book.address.dto.AddressDto;
import hongik.demo_book.address.dto.AddressResponse;
import hongik.demo_book.address.service.AddressService;
import hongik.demo_book.member.entity.Member;
import hongik.demo_book.member.service.CustomUserService;
import hongik.demo_book.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AddressController {

    private final CustomUserService customUserService;
    private final AddressService addressService;

    @PostMapping("/member/address")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<AddressDto> saveMemberAddress(
            @Valid @RequestBody AddressDto addressdto
    ) {
        Member member = customUserService.GetCurrentMember();
        return ResponseEntity.ok(addressService.AddressSave(addressdto, member));
    }

    //주소 삭제
    @DeleteMapping("/member/address")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<AddressResponse> deleteMemberAddress() {
        Member member = customUserService.GetCurrentMember();
        return ResponseEntity.ok(addressService.deleteAddress(member));
    }

    //저장된 주소 반환
    @GetMapping("/member/address")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<AddressDto> returnMemberAddress(HttpServletRequest request){
        Member member = customUserService.GetCurrentMember();
        return ResponseEntity.ok(addressService.AddressReturn(member));
    }

}
