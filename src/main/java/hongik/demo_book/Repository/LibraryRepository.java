package hongik.demo_book.Repository;


import hongik.demo_book.domain.Library;
import hongik.demo_book.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    List<Library> findAllByMember(Member member);
}
