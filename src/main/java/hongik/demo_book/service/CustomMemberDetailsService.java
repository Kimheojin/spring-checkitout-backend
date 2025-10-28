package hongik.demo_book.service;

import hongik.demo_book.member.repository.MemberRepository;
import hongik.demo_book.member.entity.Member;
import hongik.demo_book.member.dto.MemberWithAuthoritiesDto;
import hongik.demo_book.global.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("memberDetailsService")
@RequiredArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional // username 대신 email로
    public UserDetails loadUserByUsername(final String email) {
        return memberRepository.findByEmail(email)  // 이메일로 사용자 정보 검색
                .map(member -> createMember(email, member))
                .orElseThrow(NotFoundMemberException::new);
    }


    private User createMember(String email, Member member) {
        if (!member.isActivated()) {
            throw new RuntimeException(email + " -> 활성화되어 있지 않습니다.");
        }

        // 회원과 권한 정보 가져오기
        MemberWithAuthoritiesDto memberWithAuthorities =
                memberRepository.findMemberWithAuthoritiesByEmail(member.getEmail())
                        .orElseThrow(NotFoundMemberException::new);

        // 각 권한 문자열을 SimpleGrantedAuthority 객체로 변환
        List<GrantedAuthority> grantedAuthorities = memberWithAuthorities.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(member.getEmail(),
                member.getPassword(),
                grantedAuthorities);
    }
}
