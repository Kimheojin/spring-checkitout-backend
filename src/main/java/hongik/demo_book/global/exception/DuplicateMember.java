package hongik.demo_book.global.exception;

import org.springframework.http.HttpStatus;

public class DuplicateMember extends CustomRuntimeException {
    private static final String MESSAGE = "이미 존재하는 유저입니다.";
    public DuplicateMember() {
        super(MESSAGE);
    }
    @Override
    public int getstatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    } // 400
}
