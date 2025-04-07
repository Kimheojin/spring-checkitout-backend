package hongik.demo_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("dev")
public class DemoBookApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoBookApplication.class, args);
	}

}

