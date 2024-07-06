package hongik.demo_book.service;

import hongik.demo_book.Repository.MemberRepository;
import hongik.demo_book.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("memberDetailsService")
public class CustomMemberDetailsService implements UserDetailsService {  // UserDetailsService로 변경
    private final MemberRepository memberRepository;

    public CustomMemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String membername) throws UsernameNotFoundException {  // 메서드 이름 및 매개변수 변경
        return memberRepository.findOneWithAuthoritiesByMemername(membername)  // 메서드 이름 수정
                .map(member -> createUser(membername, member))
                .orElseThrow(() -> new UsernameNotFoundException(membername + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, Member member) {  // 'User' 대신 'Member' 사용
        if (!member.isActivated()) {
            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(member.getMembername(),
                member.getPassword(),
                grantedAuthorities);
    }
}
