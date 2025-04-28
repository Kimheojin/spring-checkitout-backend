package hongik.demo_book.Repository;

import hongik.demo_book.Repository.customQueryRepository.BookRepositoryCustom;
import hongik.demo_book.domain.Book;
import hongik.demo_book.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

    Optional<Book> findByIsbn13(String isbn13);
}
