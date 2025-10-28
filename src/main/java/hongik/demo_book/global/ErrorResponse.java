package hongik.demo_book.global;


import lombok.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ErrorResponse {
    private Integer statusCode;

    private String message;

    @Builder.Default // null 체크 관련
    private final Map<String, String> validation = new HashMap<>();

    public void addValidation(String field, String errorMessage) {
        this.validation.put(field, errorMessage);
    }


}
