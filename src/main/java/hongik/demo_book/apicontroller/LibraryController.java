package hongik.demo_book.apicontroller;

import hongik.demo_book.Repository.MemberRepository;
import hongik.demo_book.dto.LibraryDto;
import hongik.demo_book.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryController {



    private final LibraryService libraryService;
    public LibraryController(LibraryService libraryService, MemberRepository memberRepository) {
        this.libraryService = libraryService;

    }

    //도서관 저장
    @Operation(summary = "도서관 저장기능", description = "도서관 코드, 도서관 상태 보내주면 저장")
    @PostMapping("/member/librarySave")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<LibraryDto> saveMEmberLibrary(
            @Valid @RequestBody LibraryDto libraryDto
    ){
        return ResponseEntity.ok(libraryService.LibrarySave(libraryDto));
    }
    //도서관 삭제
    //현재 저장된 도서관 목록 반환
    @Operation(summary = "도서관 삭제기능", description = "도서관 코드, 도서관 상태 보내주면 삭제")
    @DeleteMapping("/member/libraryDelete")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<LibraryDto>> deleteMemberLibrary(
            @Valid @RequestBody LibraryDto libraryDto
    ){

        return ResponseEntity.ok(libraryService.LibraryDelete(libraryDto));
    }

    @Operation(summary = "도서관 목록 반환 기능", description = "그냥 해당 url 요청 보내면 전체 도서관 리스트 반환")
    @GetMapping("/member/libraryReturn")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<LibraryDto>> returnMemberLibrary(HttpServletRequest request){
        return ResponseEntity.ok(libraryService.LibraryList());
    }
    //도서관 목록 반환

}
