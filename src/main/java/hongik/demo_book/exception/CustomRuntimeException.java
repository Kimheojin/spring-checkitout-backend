package hongik.demo_book.exception;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class CustomRuntimeException extends RuntimeException{

    public final Map<String, String> validation = new HashMap<>();

    // 생성자
    public CustomRuntimeException(String message) {
        super(message);
    }


    // 강제 하기 위해
    public abstract int getstatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}




