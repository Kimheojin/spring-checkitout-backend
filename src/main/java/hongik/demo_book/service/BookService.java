package hongik.demo_book.service;


import hongik.demo_book.Repository.BookRepository;
import hongik.demo_book.Repository.CategoryRepository;
import hongik.demo_book.Repository.MemberRepository;
import hongik.demo_book.domain.Book;
import hongik.demo_book.domain.Category;
import hongik.demo_book.domain.Member;
import hongik.demo_book.dto.BookDto;
import hongik.demo_book.dto.BookListDto;
import hongik.demo_book.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    private final UserService userService;


    //책 삭제 후 해당 리스트 반환
    @Transactional
    public List<BookDto> BookDelete(BookDto bookDto) {
      /*  // 현재 사용자의 이메일을 가져오기
        String currentUserEmail = SecurityUtil.getCurrentEmail()
                .orElseThrow(() -> new RuntimeException("현재 사용자의 이메일을 찾을 수 없습니다."));

        // 이메일로 회원 정보 조회
        Member member = memberRepository.findOneWithAuthoritiesByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));*/

        Member member = userService.GetCurrentMmember();

        // 삭제할 도서가 속한 카테고리 찾기
        Category categoryToUpdate = member.getCategories().stream()
                .filter(category -> category.getCategoryName().equals(bookDto.getCategoryName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당 카테고리를 찾을 수 없습니다."));

        // 카테고리에서 삭제할 도서 찾기
        Book bookToDelete = categoryToUpdate.getBooks().stream()
                .filter(book -> book.getIsbn13().equals(bookDto.getIsbn13()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당 도서를 찾을 수 없습니다."));

        // 도서 삭제
        categoryToUpdate.getBooks().remove(bookToDelete);
        bookRepository.delete(bookToDelete);

        // 삭제 후 남아있는 도서 목록 반환
        List<BookDto> remainingBooks = categoryToUpdate.getBooks().stream()
                .map(book -> BookDto.builder()
                        .isbn13(book.getIsbn13())
                        .categoryName(categoryToUpdate.getCategoryName())
                        .build())
                .collect(Collectors.toList());

        return remainingBooks;
    }

    //도서 복록 반환
    //enum타입 카테고리 dto 받아서 책 목록 반환하는 식으로 해야할듯
    @Transactional(readOnly = true)
    public List<BookDto> BookList(BookListDto bookListDto) {
        /*String currentUserEmail = SecurityUtil.getCurrentEmail()
                .orElseThrow(() -> new RuntimeException("현재 사용자의 이메일을 찾을 수 없습니다."));

        Member member = memberRepository.findOneWithAuthoritiesByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
*/

        Member member = userService.GetCurrentMmember();
        Category categoryToUpdate = member.getCategories().stream()
                .filter(category -> category.getCategoryName().equals(bookListDto.getCategoryName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당 카테고리를 찾을 수 없습니다."));


        List<BookDto> remainingBooks = categoryToUpdate.getBooks().stream()
                .map(book -> BookDto.builder()
                        .isbn13(book.getIsbn13())
                        .categoryName(categoryToUpdate.getCategoryName())
                        .build())
                .collect(Collectors.toList());

        return remainingBooks;
    }


    
    //도서 저장
    @Transactional
    public BookDto BookSave(BookDto bookdto) {

        Member member = userService.GetCurrentMmember();



        Category categoryToUpdate = member.getCategories().stream()
                .filter(category -> category.getCategoryName().equals(bookdto.getCategoryName()))
                .findFirst()
                .orElse(null);

        if (categoryToUpdate == null) {
            categoryToUpdate = new Category();
            categoryToUpdate.setCategoryName(bookdto.getCategoryName());
            categoryToUpdate.setMember(member);
            categoryRepository.save(categoryToUpdate);
            member.getCategories().add(categoryToUpdate); // 사용자의 카테고리 목록에 추가
        }

        Book book = new Book();
        book.setCategory(categoryToUpdate);
        book.setIsbn13(bookdto.getIsbn13());

        bookRepository.save(book);

        // BookDto를 반환

        return new BookDto(book.getIsbn13(), book.getCategory().getCategoryName());


        //카테고리 저장하고 해야할듯 개귀찬은데

        //카테고리별로 기능 구현 해야하나?

    }
}
