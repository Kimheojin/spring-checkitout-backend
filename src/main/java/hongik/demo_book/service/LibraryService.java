package hongik.demo_book.service;


import hongik.demo_book.Repository.LibraryRepository;
import hongik.demo_book.domain.Library;
import hongik.demo_book.domain.Member;
import hongik.demo_book.dto.LibraryDto;
import hongik.demo_book.exception.NotFoundLibrary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final CustomUserService customUserService;
    //도서관 저장
    @Transactional
    public LibraryDto LibrarySave(LibraryDto libraryDto) {

        Member member = customUserService.GetCurrentMember();
        //status에 따라 저장하기
        Library library = Library.builder()
                .libraryStatus(libraryDto.getLibraryStatus())
                .libraryCode(libraryDto.getLibraryCode())
                .member(member)
                .build();

        libraryRepository.save(library);

        return new LibraryDto(library.getLibraryCode(), library.getLibraryStatus());

    }

    //도서관 삭제
    @Transactional
    public List<LibraryDto> LibraryDelete(LibraryDto libraryDto) {

        Member member = customUserService.GetCurrentMember();

        Library libraryToDelete = libraryRepository.FindLibrarhWithMember(member)
                .stream()
                .filter(library -> library.getLibraryCode().equals(libraryDto.getLibraryCode()))
                .filter(library -> library.getLibraryStatus().equals(libraryDto.getLibraryStatus()))
                .findFirst()
                .orElseThrow(NotFoundLibrary::new);

        libraryRepository.delete(libraryToDelete);

        return LibraryList();
        //현재 저장 도서관 반환

    }

    //도서관 리스트 반환
    @Transactional(readOnly = true)
    public List<LibraryDto> LibraryList() {

        Member member = customUserService.GetCurrentMember();
        // 회원과 매핑된 도서관 리스트 가져오기
        //그거 사용
        List<Library> libraries = libraryRepository.findAllByMember(member);

        // Library 엔티티를 LibraryDto로 변환
        return libraries.stream()
                .map(library -> new LibraryDto(library.getLibraryCode(), library.getLibraryStatus()))
                .collect(Collectors.toList());
    }
}
