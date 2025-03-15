package hongik.demo_book.Repository;

import hongik.demo_book.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Optional<Authority> findByAuthorityName(String authorityName);
}
