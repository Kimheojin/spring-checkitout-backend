package hongik.demo_book.service;


import hongik.demo_book.Repository.AuthorityRepository;
import hongik.demo_book.Repository.MemberRepository;
import hongik.demo_book.domain.Address;
import hongik.demo_book.domain.Authority;
import hongik.demo_book.domain.Member;
import hongik.demo_book.domain.MemberAuthority;
import hongik.demo_book.dto.AddressDto;
import hongik.demo_book.dto.MemberDto;
import hongik.demo_book.exception.DuplicateMember;
import hongik.demo_book.exception.NotFoundMemberException;
import hongik.demo_book.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserService customUserService;
    private final AuthorityRepository authorityRepository;
    @Transactional
    public MemberDto signup(MemberDto memberDto) {
        if (memberRepository.findOneWithAuthoritiesByEmail(memberDto.getEmail()).orElse(null) != null) {
            throw new DuplicateMember();
        }

        Authority authority = authorityRepository.findByAuthorityName("ROLE_USER").orElseThrow(
                () -> new RuntimeException("기본 권한을 찾을 수 없습니다."));


        Member member = Member.builder()
                .membername(memberDto.getMembername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .email(memberDto.getEmail())
                .activated(true)
                .build();

        member = memberRepository.save(member);
        MemberAuthority memberAuthority = MemberAuthority.builder()
                .authority(authority)
                .member(member)  // Member 설정
                .build();
        member.getMemberAuthorities().add(memberAuthority);

        return MemberDto.from(member);


    }

    @Transactional(readOnly = true)
    public MemberDto getMemberWithAuthorities(String email) {
        return MemberDto.from(memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
    }

    //읽기전용
    @Transactional(readOnly = true)
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(
                SecurityUtil.getCurrentEmail()
                        .flatMap(memberRepository::findOneWithAuthoritiesByEmail)
                        .orElseThrow(() -> new NotFoundMemberException())
        );
    }

    //쓰기전용
    @Transactional
    public AddressDto AddressSave(AddressDto addressdto) {


        Member member = customUserService.GetCurrentMmember();

        Address address = Address.builder()
                .city(addressdto.getCity())
                .street(addressdto.getStreet())
                .zipcode(addressdto.getZipcode())
                .build();

        member.updateAddress(address);

        Address savedAddress = member.getAddress();

        return AddressDto.builder()
                .city(savedAddress.getCity())
                .street(savedAddress.getStreet())
                .zipcode(savedAddress.getZipcode())
                .build();
    }


    //주소 삭제
    @Transactional
    public void deleteAddress() {

        Member member = customUserService.GetCurrentMmember();

        Address currentAddress = member.getAddress();

        if(currentAddress == null){
            throw new RuntimeException("주소가 설정되어있지 않습니다");
        }
        member.updateAddress(null);

    }

    //주소 목록 반환
    @Transactional(readOnly = true)
    public AddressDto AddressReturn(){

        Address address = customUserService.GetCurrentMmember().getAddress();
        return AddressDto.builder()
                .zipcode(address.getZipcode())
                .street(address.getZipcode())
                .city(address.getZipcode())
                .build();
    }
}
