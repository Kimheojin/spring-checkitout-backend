package hongik.demo_book.book.repository;

import hongik.demo_book.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

    Optional<Book> findByIsbn13(String isbn13);
}
