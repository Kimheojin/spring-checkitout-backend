package hongik.demo_book.exception;

import org.springframework.http.HttpStatus;

public class CustomNotFound extends CustomRuntimeException{


    private static final String MESSAGE = " 찾을 수 없습니다.";

    public CustomNotFound(String entity) {super(entity + MESSAGE); };


    @Override
    public int getstatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
