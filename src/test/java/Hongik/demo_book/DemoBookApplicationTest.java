package Hongik.demo_book;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;


@SpringBootTest
@Profile("dev")
class DemoBookApplicationTest {


    @Test
    void contextLoads() {
    }

}