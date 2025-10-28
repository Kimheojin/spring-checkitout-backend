package hongik.demo_book.library.repository;


import hongik.demo_book.library.entity.Library;
import hongik.demo_book.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibraryRepository extends JpaRepository<Library, Long>, LibraryRepositoryCustom {

    List<Library> findAllByMember(Member member);
}
