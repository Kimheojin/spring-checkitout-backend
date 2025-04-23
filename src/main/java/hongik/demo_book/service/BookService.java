package hongik.demo_book.service;


import hongik.demo_book.Repository.BookRepository;
import hongik.demo_book.Repository.CategoryRepository;
import hongik.demo_book.domain.Book;
import hongik.demo_book.domain.Category;
import hongik.demo_book.domain.Member;
import hongik.demo_book.dto.BookDto;
import hongik.demo_book.dto.BookListDto;
import hongik.demo_book.exception.NotFoundBook;
import hongik.demo_book.exception.NotFoundCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final CustomUserService customUserService;


    //책 삭제 후 해당 리스트 반환
    @Transactional
    public List<BookDto> BookDelete(BookDto bookDto) {


        Member member = customUserService.GetCurrentMember();

        List<Category> categories = categoryRepository.findCategoriesWithMember(member);

        // 삭제할 도서가 속한 카테고리 찾기
        Category categoryToUpdate = categories.stream()
                .filter(category -> category.getCategoryName().equals(bookDto.getCategoryName()))
                .findFirst()
                .orElseThrow(NotFoundCategory::new);



        // 카테고리에서 삭제할 도서 찾기
        Book bookToDelete = bookRepository.findBookBycategoryname(String.valueOf(categoryToUpdate.getCategoryName()), member)
                .stream()
                .filter(book -> book.getIsbn13().equals(bookDto.getIsbn13()))
                .findFirst()
                .orElseThrow(NotFoundBook::new);

        // 도서 삭제

        bookRepository.delete(bookToDelete);

        // 삭제 후 남아있는 도서 목록 반환
        List<BookDto> remainingBooks = bookRepository.findBookBycategoryname(categoryToUpdate.getCategoryName().toString(), member).stream()
                .map(book -> BookDto.builder()
                        .isbn13(book.getIsbn13())
                        .categoryName(categoryToUpdate.getCategoryName())
                        .build())
                .collect(Collectors.toList());

        return remainingBooks;
    }

    //도서 목록 반환
    //enum타입 카테고리 dto 받아서 책 목록 반환하는 식으로 해야할듯
    @Transactional(readOnly = true)
    public List<BookDto> BookList(BookListDto bookListDto) {

        Member member = customUserService.GetCurrentMember();

        List<Category> categories = categoryRepository.findCategoriesWithMember(member);

        Category categoryToUpdate = categories.stream()
                .filter(category -> category.getCategoryName().equals(bookListDto.getCategoryName()))
                .findFirst()
                .orElseThrow(NotFoundCategory::new);


        List<BookDto> remainingBooks = bookRepository.findBookBycategoryname(categoryToUpdate.getCategoryName().toString(), member).stream()
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

        Member member = customUserService.GetCurrentMember();




        // 이미 존재하는 겨우
        Category category = categoryRepository.findCategoriesWithMember(member).stream()
                .filter(c -> c.getCategoryName().equals(bookdto.getCategoryName()))
                .findFirst().orElseGet(
                        () -> {
                            Category newCategory = Category.builder()
                                    .categoryName(bookdto.getCategoryName())
                                    .member(member)
                                    .build();
                            return categoryRepository.save(newCategory);
                        });


        Book book = Book.builder()
                .isbn13(bookdto.getIsbn13())
                .category(category)
                .build();

        bookRepository.save(book);

        // BookDto를 반환
        return new BookDto(book.getIsbn13(), book.getCategory().getCategoryName());


    }
}
