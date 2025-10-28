package hongik.demo_book.cateogory.Repository;

import hongik.demo_book.cateogory.service.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
}
