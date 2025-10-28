package hongik.demo_book.global.exception;

public class NoRoleUser extends CustomRuntimeException{

    private static final String MESSAGE = "기본 권한이 없습니다.";
    public NoRoleUser() {
        super(MESSAGE);
    }
    @Override
    public int getstatusCode() {
        return 403;
    }
}
