package hongik.demo_book.service;


import hongik.demo_book.Repository.MemberRepository;
import hongik.demo_book.domain.Member;
import hongik.demo_book.exception.NotFoundMemberException;
import hongik.demo_book.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService {

    private final MemberRepository memberRepository;

    public Member GetCurrentMember() {
        String currentUserEmail = SecurityUtil.getCurrentEmail()
                .orElseThrow(NotFoundMemberException::new);

        return memberRepository.findByMEmail(currentUserEmail)
                .orElseThrow(NotFoundMemberException::new);
    }

}
