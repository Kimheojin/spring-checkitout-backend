package hongik.demo_book.Repository.customQueryRepository;

import hongik.demo_book.domain.Category;
import hongik.demo_book.domain.Member;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> findCategoriesWithMember(Member member);
}
