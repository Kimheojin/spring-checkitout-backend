package hongik.demo_book.init;


import hongik.demo_book.Repository.AuthorityRepository;
import hongik.demo_book.Repository.MemberRepository;
import hongik.demo_book.domain.Authority;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class insertInitialDbData {


    private final AuthorityRepository authorityRepository;
    private final Logger logger = LoggerFactory.getLogger(insertInitialDbData.class);

    @PostConstruct
    public void init(){
        try {
            initializeAuthoritiees();
            logger.info("Authority 초기화 완료");
        } catch (Exception e){
            logger.error("Authority 초기화 중 오류 발생  : {}", e.getMessage());
        }
    }

    private void initializeAuthoritiees() {
        if(authorityRepository.findByAuthorityName("ROLE_USER").isEmpty()){
            Authority authority = Authority.builder()
                    .authorityName("ROLE_USER")
                    .build();

            authorityRepository.save(authority);

            logger.info("ROLE_USER 권한 생성");
        }

        if(authorityRepository.findByAuthorityName("ROLE_ADMIN").isEmpty()){
            Authority authority = Authority.builder()
                    .authorityName("ROLE_ADMIN")
                    .build();

            authorityRepository.save(authority);

            logger.info("ROLE_ADMIN 권한 생성");
        }
    }

}
