package hongik.demo_book.exception;

import org.springframework.http.HttpStatus;

public class NotFoundLibrary extends CustomRuntimeException{

    private static final String MESSAGE = "해당 도서관을 찾을 수 없습니다.";

    public NotFoundLibrary( ) {
        super(MESSAGE);
    }

    @Override
    public int getstatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
