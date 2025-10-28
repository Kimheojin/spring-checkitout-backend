package hongik.demo_book.member.service;


import hongik.demo_book.member.repository.MemberRepository;
import hongik.demo_book.member.entity.Member;
import hongik.demo_book.global.exception.NotFoundMemberException;
import hongik.demo_book.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService {

    private final MemberRepository memberRepository;

    public Member GetCurrentMember() {
        String currentUserEmail = SecurityUtil.getCurrentEmail()
                .orElseThrow(NotFoundMemberException::new);

        return memberRepository.findByEmail(currentUserEmail)
                .orElseThrow(NotFoundMemberException::new);
    }

}
