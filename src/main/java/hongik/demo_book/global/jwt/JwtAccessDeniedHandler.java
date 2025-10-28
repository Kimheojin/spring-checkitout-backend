package hongik.demo_book.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


// 필터 단 예외 handler
@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException {
        //필터단은 추상화 그게 낮아서 json 직접 생성 필요
        int status = HttpServletResponse.SC_FORBIDDEN;

        response.setStatus(status); // 403
        response.setContentType("application/json;charset=UTF-8"); // Content-Type 헤더를 설정

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", status);
        errorResponse.put("message", "접근에 대한 권한이 X");


        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        PrintWriter writer = response.getWriter(); // 응답 본문에 텍스트를 쓰기 위해
        // 응답 객체에서 출력 스트림 제공
        writer.write(jsonResponse);
        writer.flush();
    }
}
