package hongik.demo_book.controller;

import hongik.demo_book.domain.Member;
import hongik.demo_book.dto.LibraryDto;
import hongik.demo_book.service.CustomUserService;
import hongik.demo_book.service.LibraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;
    private final CustomUserService customUserService;
    //도서관 저장
    @PostMapping("/member/librarys")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<LibraryDto> saveMEmberLibrary(
            @Valid @RequestBody LibraryDto libraryDto
    ){
        Member member = customUserService.GetCurrentMember();
        return ResponseEntity.ok(libraryService.LibrarySave(libraryDto, member));
    }
    //도서관 삭제
    //현재 저장된 도서관 목록 반환

    @DeleteMapping("/member/librarys")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<LibraryDto>> deleteMemberLibrary(
            @Valid @RequestBody LibraryDto libraryDto
    ){
        Member member = customUserService.GetCurrentMember();
        return ResponseEntity.ok(libraryService.LibraryDelete(libraryDto, member));
    }

    @GetMapping("/member/librarys")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<LibraryDto>> returnMemberLibrary(){
        Member member = customUserService.GetCurrentMember();
        return ResponseEntity.ok(libraryService.LibraryList(member));
    }


}
