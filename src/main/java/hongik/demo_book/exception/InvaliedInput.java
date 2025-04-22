package hongik.demo_book.exception;


import org.springframework.http.HttpStatus;

public class InvaliedInput extends CustomRuntimeException{

    private static final String MESSAGE = "잘못된 입력입니다.";
    public InvaliedInput() {
        super(MESSAGE);
    }
    @Override
    public int getstatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
