package hongik.demo_book.config;


import hongik.demo_book.Repository.AuthorityRepository;
import hongik.demo_book.Repository.MemberAuthorityRepository;
import hongik.demo_book.Repository.MemberRepository;
import hongik.demo_book.domain.Authority;
import hongik.demo_book.domain.Member;
import hongik.demo_book.domain.MemberAuthority;
import hongik.demo_book.dto.repoDto.MemberWithAuthoritiesDto;
import hongik.demo_book.exception.NoRoleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DemoMockSecurityContext implements WithSecurityContextFactory<WithMockBookUser> {

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberAuthorityRepository memberAuthorityRepository;


    @Override
    public SecurityContext createSecurityContext(WithMockBookUser annotation) {
        String email = annotation.email(); // annotation 한번 가공 되어서 value로
        String password = annotation.password();
        String membername = annotation.membername(); // 마찬가지 membername
        String[] roles = annotation.roles();


        SecurityContext context = SecurityContextHolder.createEmptyContext();

        MemberWithAuthoritiesDto testMember = memberRepository.findMemberWithAuthoritiesByEmail(email)
                .orElseGet(() -> createMember(email, password, membername, roles));


        List<SimpleGrantedAuthority> authorities =
                memberRepository.findMemberWithAuthoritiesByEmail(testMember.getEmail())
                        .stream()
                .map(memberAuth -> new SimpleGrantedAuthority(memberAuth.getAuthorities().get(0)))
                .collect(Collectors.toList());



        Authentication auth = new UsernamePasswordAuthenticationToken(testMember.getEmail(), null, authorities);
        context.setAuthentication(auth);

        return context;
    }



    private MemberWithAuthoritiesDto createMember(String email, String password, String membername, String[] role) {


        Authority authority = authorityRepository.findByAuthorityName(role[0]).orElseThrow(
                () -> new NoRoleUser());


        Member member = Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .memberName(membername)
                .activated(true)
                .build();


        MemberAuthority memberAuthority = MemberAuthority.builder()
                .authority(authority)
                .member(member)  // Member 설정
                .build();
        memberAuthorityRepository.save(memberAuthority);
        memberRepository.save(member);

        // 저장 후 반환
        return MemberWithAuthoritiesDto.builder()
                .activated(member.isActivated())
                .address(member.getAddress())
                .id(member.getId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .authorities(List.of(memberAuthority.toString()))
                .password(member.getPassword()).build();
    }
}