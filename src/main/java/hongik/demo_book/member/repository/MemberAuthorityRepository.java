package hongik.demo_book.member.repository;


import hongik.demo_book.member.entity.MemberAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAuthorityRepository extends JpaRepository<MemberAuthority, Long> {
}
