package hongik.demo_book.service;


import hongik.demo_book.Repository.MemberRepository;
import hongik.demo_book.domain.Member;
import hongik.demo_book.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService {

    private final MemberRepository memberRepository;

    public Member GetCurrentMmember() {
        String currentUserEmail = SecurityUtil.getCurrentEmail()
                .orElseThrow(() -> new RuntimeException("현재 사용자의 이메일을 찾을 수 없습니다."));

        return memberRepository.findOneWithAuthoritiesByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

}
