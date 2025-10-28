package hongik.demo_book.book.repository;

import hongik.demo_book.book.entity.Book;
import hongik.demo_book.member.entity.Member;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findBookByCategoryName(String categoryName, Member member);
}
