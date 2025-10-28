package hongik.demo_book.global.exception;

import org.springframework.http.HttpStatus;

public class NotFoundMemberaddress extends CustomRuntimeException{

    private static final String MESSAGE = "존재하는 address 값이 없습니다.";

    public NotFoundMemberaddress() {
        super(MESSAGE);
    }
    @Override
    public int getstatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
