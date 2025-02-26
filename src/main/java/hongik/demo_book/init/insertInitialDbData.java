package hongik.demo_book.init;


import hongik.demo_book.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class insertInitialDbData {


    private final MemberRepository memberRepository;


}
