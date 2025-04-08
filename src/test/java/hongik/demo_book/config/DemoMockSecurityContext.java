package hongik.demo_book.config;


import hongik.demo_book.Repository.AuthorityRepository;
import hongik.demo_book.Repository.MemberRepository;
import hongik.demo_book.domain.Authority;
import hongik.demo_book.domain.Member;
import hongik.demo_book.domain.MemberAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DemoMockSecurityContext implements WithSecurityContextFactory<WithMockUser> {

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public SecurityContext createSecurityContext(WithMockUser annotation) {
        String email = annotation.value(); // annotation 한번 가공 되어서 value로
        String password = annotation.password();
        String membername = annotation.username(); // 마찬가지 membername
        String[] roles = annotation.roles();


        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Member testMember = memberRepository.findOneWithAuthoritiesByEmail(email)
                .orElseGet(() -> createMember(email, password, membername, roles));


        List<SimpleGrantedAuthority> authorities = testMember.getMemberAuthorities().stream()
                .map(memberAuth -> new SimpleGrantedAuthority(memberAuth.getAuthority().getAuthorityName()))
                .collect(Collectors.toList());


        User principal = new User(testMember.getEmail(), testMember.getPassword(), authorities);


        Authentication auth = new UsernamePasswordAuthenticationToken(testMember.getEmail(), null, authorities);
        context.setAuthentication(auth);

        return context;
    }

    private Member createMember(String email, String password, String membername, String[] roles) {


        Set<MemberAuthority> memberAuthorities = new HashSet<>();

        for (String role : roles) {
            Authority authority = authorityRepository.findByAuthorityName(role)
                    .orElseGet(() -> authorityRepository.save(Authority.builder().authorityName(role).build()));

            MemberAuthority memberAuthority = MemberAuthority.builder()
                    .authority(authority)
                    .build();

            memberAuthorities.add(memberAuthority);
        }


        Member member = Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .membername(membername)
                .activated(true)
                .memberAuthorities(memberAuthorities)
                .build();

        for (MemberAuthority memberAuthority : memberAuthorities) {
            memberAuthority = MemberAuthority.builder()
                    .member(member)
                    .authority(memberAuthority.getAuthority())
                    .build();
        }

        // 저장 후 반환
        return memberRepository.save(member);
    }
}