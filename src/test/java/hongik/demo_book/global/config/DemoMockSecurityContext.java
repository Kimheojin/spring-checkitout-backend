package hongik.demo_book.global.config;


import hongik.demo_book.member.repository.AuthorityRepository;
import hongik.demo_book.member.repository.MemberAuthorityRepository;
import hongik.demo_book.member.repository.MemberRepository;
import hongik.demo_book.member.entity.Authority;
import hongik.demo_book.member.entity.Member;
import hongik.demo_book.member.entity.MemberAuthority;
import hongik.demo_book.member.dto.MemberWithAuthoritiesDto;
import hongik.demo_book.global.exception.NoRoleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.transaction.annotation.Transactional;


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
        String memberName = annotation.memberName(); // 마찬가지 membername
        String[] roles = annotation.roles();


        SecurityContext context = SecurityContextHolder.createEmptyContext();

        MemberWithAuthoritiesDto testMember = memberRepository.findMemberWithAuthoritiesByEmail(email)
                .orElseGet(() -> createMember(email, password, memberName, roles));


        List<SimpleGrantedAuthority> authorities = testMember.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());



        Authentication auth = new UsernamePasswordAuthenticationToken(testMember.getEmail(), null, authorities);
        context.setAuthentication(auth);

        return context;
    }




    @Transactional
    protected MemberWithAuthoritiesDto createMember(String email, String password, String memberName, String[] role) {


        Authority authority = authorityRepository.findByAuthorityName(role[0])
                .orElseThrow(NoRoleUser::new);


        Member member = Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .memberName(memberName)
                .activated(true)
                .build();


        MemberAuthority memberAuthority = MemberAuthority.builder()
                .authority(authority)
                .member(member)  // Member 설정
                .build();

        memberRepository.save(member);
        memberAuthorityRepository.save(memberAuthority);


        // 저장 후 반환
        return MemberWithAuthoritiesDto.builder()
                .activated(member.isActivated())
                .address(member.getAddress())
                .id(member.getId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .authorities(List.of(authority.getAuthorityName()))
                .password(member.getPassword())
                .build();
    }
}