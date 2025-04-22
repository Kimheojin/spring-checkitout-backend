package hongik.demo_book.exception;


import org.springframework.http.HttpStatus;

public class NotFoundCategory extends CustomRuntimeException{

    private static final String MESSAGE = "존재하는 카테고리를 찾을 수 없습니다.";

    public NotFoundCategory (){
        super(MESSAGE);
    }
    @Override
    public int getstatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
