package hongik.demo_book.Repository;


import hongik.demo_book.domain.MemberAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAuthorityRepository extends JpaRepository<MemberAuthority, Long> {
}
