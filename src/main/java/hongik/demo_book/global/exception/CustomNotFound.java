package hongik.demo_book.global.exception;

import org.springframework.http.HttpStatus;

public class CustomNotFound extends CustomRuntimeException{


    private static final String MESSAGE = "해당 내용을 찾을 수 없습니다. ";

    public CustomNotFound(String entity) {super(MESSAGE + "(" + entity + ")"); };


    @Override
    public int getstatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
