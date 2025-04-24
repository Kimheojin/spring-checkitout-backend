package hongik.demo_book.config;


import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = DemoMockSecurityContext.class)
public @interface WithMockBookUser{


    // 이메일 대신 사용
    String email() default "huhiiijin1109@naver.com";
    String password() default "champsWinnerChelsea";
    String memberName() default "HeoJinJin";
    String[] roles() default {"ROLE_USER"};


    // mock 유저 설정 관련
}
