package hongik.demo_book.service;


import hongik.demo_book.Repository.LibraryRepository;
import hongik.demo_book.domain.Library;
import hongik.demo_book.domain.Member;
import hongik.demo_book.dto.LibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final UserService userService;
    //도서관 저장
    @Transactional
    public LibraryDto LibrarySave(LibraryDto libraryDto) {

        Member member = userService.GetCurrentMmember();
        //status에 따라 저장하기
        Library library = Library.builder()
                .library_status(libraryDto.getLibrary_status())
                .library_code(libraryDto.getLibrary_code())
                .member(member)
                .build();

        libraryRepository.save(library);

        return new LibraryDto(library.getLibrary_code(), library.getLibrary_status());

    }

    //도서관 삭제
    @Transactional
    public List<LibraryDto> LibraryDelete(LibraryDto libraryDto) {

        Member member = userService.GetCurrentMmember();

        Library libraryToDelete = member.getLibrarys().stream()
                .filter(library -> library.getLibrary_code().equals(libraryDto.getLibrary_code()))
                .filter(library -> library.getLibrary_status().equals(libraryDto.getLibrary_status()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당 도서관을 찾을 수 없습니다."));

        libraryRepository.delete(libraryToDelete);

        return LibraryList();
        //현재 저장 도서관 반환

    }

    //도서관 리스트 반환
    @Transactional(readOnly = true)
    public List<LibraryDto> LibraryList() {

        Member member = userService.GetCurrentMmember();
        // 회원과 매핑된 도서관 리스트 가져오기
        //그거 사용
        List<Library> libraries = libraryRepository.findAllByMember(member);

        // Library 엔티티를 LibraryDto로 변환
        return libraries.stream()
                .map(library -> new LibraryDto(library.getLibrary_code(), library.getLibrary_status()))
                .collect(Collectors.toList());
    }
}
