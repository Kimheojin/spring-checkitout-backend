package hongik.demo_book.exception;

public class NotFoundMemberException extends RuntimeException {

    public NotFoundMemberException(String message) {
        super(message);
    }

}
