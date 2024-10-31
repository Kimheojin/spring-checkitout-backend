package hongik.demo_book.apicontroller;


//아직 구현 X
//로그인 비밀번호 변경 + 주소까지 반환 만들고 그거하기

import hongik.demo_book.domain.CategoryName;
import hongik.demo_book.dto.BookDto;
import hongik.demo_book.dto.BookListDto;
import hongik.demo_book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    //도서 저장
    //도서 목록 반환(enum 별로 )
    //도서 삭제(리스트로 하는게 좋을듯)
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    //도서 저장
    @Operation(summary = "책 저장기능", description = "isbn13, categoryname 보내주면 저장")
    @PostMapping("/member/bookSave")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<BookDto> saveMemberBook(
            @Valid @RequestBody BookDto bookdto
    ){
        return ResponseEntity.ok(bookService.BookSave(bookdto));
    }

    //도서 목록 반환(enum타입 별로)
/*    @Operation(summary = "책 리스트 반환기능", description = "categoryname 보내주면 해당 카테고리 리스트 반환")
    @GetMapping("/member/bookList")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<BookDto>> MemberBookList(
            @Valid @RequestBody BookListDto bookListDto
    ){
        return ResponseEntity.ok(bookService.BookList(bookListDto));
    }*/

    @Operation(summary = "책 리스트 반환기능", description = "categoryname 보내주면 해당 카테고리 리스트 반환")
    @GetMapping("/member/bookList")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<BookDto>> MemberBookList(
            @Valid @RequestParam String categoryName
    ){

        CategoryName categoryEnum;
        try {
            categoryEnum = CategoryName.valueOf(categoryName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        BookListDto bookListDto = BookListDto.builder()
                .categoryName(categoryEnum)  // Enum 타입으로 설정
                .build();
        return ResponseEntity.ok(bookService.BookList(bookListDto));
    }


    //도서 삭제
    @Operation(summary = "책 삭제 기능", description = "isbn13, categoryname 보내주면 삭제")
    @DeleteMapping("/member/bookDelete")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<BookDto>> MemberBookDelete(
            @Valid @RequestBody BookDto bookDto
    ){
        return ResponseEntity.ok(bookService.BookDelete(bookDto));
    }
}
