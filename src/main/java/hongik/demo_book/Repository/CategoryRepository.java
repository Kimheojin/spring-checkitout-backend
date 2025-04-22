package hongik.demo_book.Repository;

import hongik.demo_book.Repository.customQueryRepository.CategoryRepositoryCustom;
import hongik.demo_book.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
}
