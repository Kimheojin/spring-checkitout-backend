package hongik.demo_book.Repository;


import hongik.demo_book.domain.Library;
import hongik.demo_book.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    List<Library> findAllByMember(Member member);
}
