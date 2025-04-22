package hongik.demo_book.exception;


import org.springframework.http.HttpStatus;

public class NotFoundBook extends CustomRuntimeException{

    private static final String MESSAGE = "해당 도서를 찾을 수 없습니다.";

    public NotFoundBook () {
        super(MESSAGE);
    }


    @Override
    public int getstatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
