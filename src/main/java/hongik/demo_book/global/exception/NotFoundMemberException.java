package hongik.demo_book.global.exception;

import org.springframework.http.HttpStatus;

public class NotFoundMemberException extends CustomRuntimeException {
    private static final String MESSAGE = "일치하는 Member 인증 객체가 존재하지 않습니다.";

    public NotFoundMemberException( ) {
        super(MESSAGE);
    }

    @Override
    public int getstatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }

}
