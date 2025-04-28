package hongik.demo_book.service;


import hongik.demo_book.Repository.BookRepository;
import hongik.demo_book.Repository.CategoryRepository;
import hongik.demo_book.Repository.MemberRepository;
import hongik.demo_book.domain.Book;
import hongik.demo_book.domain.CategoryName;
import hongik.demo_book.domain.Member;
import hongik.demo_book.dto.BookCategoryRequest;
import hongik.demo_book.dto.BookDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;



    private Member testMember;
    private BookDto testBookDto;
    @BeforeEach
    void setUp() {
         testMember = Member.builder()
                .memberName("테스트")
                .email("test@naver.com")
                .password("asdf")
                .activated(true)
                .build();

        memberRepository.save(testMember);

        testBookDto = BookDto.builder()
                .isbn13("1232332323")
                .categoryName(CategoryName.valueOf("FAVORITE")).build();


    }

    @Test
    @DisplayName("BookSave 정상 작동 테스트")
    void test2() {

        // given

        // when
        bookService.BookSave(testBookDto, testMember);

        // then
        Optional<Book> testBook = bookRepository.findByIsbn13(testBookDto.getIsbn13());

        assertTrue(testBook.isPresent());
        assertEquals(testBookDto.getIsbn13(), testBook.get().getIsbn13());
        assertEquals(testBookDto.getCategoryName(), testBook.get().getCategory().getCategoryName());
        assertEquals(testMember.getId(), testBook.get().getCategory().getMember().getId());

    }



    @Test
    @DisplayName("BookDelete 정상 작동 테스트")
    void test1 () {
        //given
        bookService.BookSave(testBookDto, testMember);

        // when
        bookService.BookDelete(testBookDto, testMember);

        //then

        Optional<Book> testBook = bookRepository.findByIsbn13(testBookDto.getIsbn13());

        assertTrue(testBook.isEmpty());
        assertTrue(memberRepository.findByEmail(testMember.getEmail()).isPresent());
        assertTrue(categoryRepository.findCategoriesWithMember(memberRepository.findByEmail(testMember.getEmail())
                .get()).stream()
                .filter(category -> category.getCategoryName().equals(testBookDto.getCategoryName()))
                .count() == 1);

    }

    @Test
    @DisplayName("BookList 정상 작동 테스트")
    void test3() {
        // given
        BookCategoryRequest bookCategoryRequest = new BookCategoryRequest(CategoryName.valueOf("FAVORITE"));

        bookService.BookSave(testBookDto, testMember);

        // when

        List<BookDto> testbooklist = bookService.BookList(bookCategoryRequest, testMember);

        // then

        assertFalse(testbooklist.isEmpty());
        assertEquals(testBookDto.getIsbn13(),testbooklist.get(0).getIsbn13() );



    }


}
