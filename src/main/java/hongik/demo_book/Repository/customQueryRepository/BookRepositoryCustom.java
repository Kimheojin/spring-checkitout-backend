package hongik.demo_book.Repository.customQueryRepository;

import hongik.demo_book.domain.Book;
import hongik.demo_book.domain.Member;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findBookByCategoryName(String categoryName, Member member);
}
