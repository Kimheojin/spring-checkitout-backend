package hongik.demo_book.init;


import hongik.demo_book.Repository.AuthorityRepository;
import hongik.demo_book.domain.Authority;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class insertInitialDbData {

    private final AuthorityRepository authorityRepository;

    @PostConstruct
    public void init(){
        try {
            initializeAuthoritiees();
            log.info("Authority 초기화 완료");
        } catch (Exception e){
            log.error("Authority 초기화 중 오류 발생  : {}", e.getMessage());
        }
    }

    public void initializeAuthoritiees() {
        if(authorityRepository.findByAuthorityName("ROLE_USER").isEmpty()){
            Authority authority = Authority.builder()
                    .authorityName("ROLE_USER")
                    .build();

            authorityRepository.save(authority);

            log.info("ROLE_USER 권한 생성");
        }

        if(authorityRepository.findByAuthorityName("ROLE_ADMIN").isEmpty()){
            Authority authority = Authority.builder()
                    .authorityName("ROLE_ADMIN")
                    .build();

            authorityRepository.save(authority);

            log.info("ROLE_ADMIN 권한 생성");
        }
    }

}
