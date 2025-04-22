package hongik.demo_book.Repository.customQueryRepository;

import hongik.demo_book.domain.Book;
import hongik.demo_book.domain.Member;
import hongik.demo_book.dto.BookDto;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findBookBycategoryname (String categoryname, Member member);
}
