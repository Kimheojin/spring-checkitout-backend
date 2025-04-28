package hongik.demo_book.handler;

import hongik.demo_book.dto.ErrorResponse;
import hongik.demo_book.exception.CustomRuntimeException;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleCustomRuntimeException(CustomRuntimeException e){
        Integer statusCode = e.getstatusCode();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(statusCode)
                .message(e.getMessage())
                .build();
        
        if (!e.getValidation().isEmpty()) {
            e.getValidation().forEach(errorResponse::addValidation);
        }

        return ResponseEntity.status(statusCode).body(errorResponse);
    }

}
