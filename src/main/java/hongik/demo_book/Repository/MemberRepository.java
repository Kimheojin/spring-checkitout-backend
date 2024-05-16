package hongik.demo_book.Repository;

import hongik.demo_book.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {//Long은 기본키의 데이터타입, member는 그거
    // Custom query methods (if any) can be added here
}