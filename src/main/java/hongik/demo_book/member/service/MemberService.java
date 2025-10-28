package hongik.demo_book.member.service;


import hongik.demo_book.member.repository.AuthorityRepository;
import hongik.demo_book.member.repository.MemberAuthorityRepository;
import hongik.demo_book.member.repository.MemberRepository;
import hongik.demo_book.address.entity.Address;
import hongik.demo_book.member.entity.Authority;
import hongik.demo_book.member.entity.Member;
import hongik.demo_book.member.entity.MemberAuthority;
import hongik.demo_book.address.dto.AddressDto;
import hongik.demo_book.member.dto.MemberDto;
import hongik.demo_book.member.dto.MemberWithAuthoritiesDto;
import hongik.demo_book.address.dto.AddressResponse;
import hongik.demo_book.global.exception.DuplicateMember;
import hongik.demo_book.global.exception.NotFoundMemberException;
import hongik.demo_book.global.exception.NotFoundMemberaddress;
import hongik.demo_book.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final MemberAuthorityRepository memberAuthorityRepository;
    @Transactional
    public MemberDto signup(MemberDto memberDto) {
        if (memberRepository.findMemberWithAuthoritiesByEmail(memberDto.getEmail()).orElse(null) != null) {
            throw new DuplicateMember();
        }

        Authority authority = authorityRepository.findByAuthorityName("ROLE_USER").orElseThrow(
                () -> new RuntimeException("기본 권한을 찾을 수 없습니다."));


        Member member = Member.builder()
                .memberName(memberDto.getMemberName())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .email(memberDto.getEmail())
                .activated(true)
                .build();

        memberRepository.save(member);
        MemberAuthority memberAuthority = MemberAuthority.builder()
                .authority(authority)
                .member(member)  // Member 설정
                .build();
        memberAuthorityRepository.save(memberAuthority);



        MemberWithAuthoritiesDto memberWithAuthoritiesDto = MemberWithAuthoritiesDto.builder()
                .id(member.getId())
                .authorities(List.of(memberAuthority.getAuthority().getAuthorityName()))
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .address(member.getAddress())
                .activated(member.isActivated())
                .build();


        return MemberDto.from(memberWithAuthoritiesDto);


    }

    @Transactional(readOnly = true)
    public MemberDto getMemberWithAuthorities(String email) {
        return MemberDto.from(memberRepository.findMemberWithAuthoritiesByEmail(email).orElse(null));
    }

    //읽기전용
    @Transactional(readOnly = true)
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(
                SecurityUtil.getCurrentEmail()
                        .flatMap(memberRepository::findMemberWithAuthoritiesByEmail)
                        .orElseThrow(NotFoundMemberException::new)
        );
    }


}
