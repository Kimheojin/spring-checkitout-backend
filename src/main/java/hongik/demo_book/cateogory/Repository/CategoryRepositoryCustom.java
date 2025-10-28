package hongik.demo_book.cateogory.Repository;

import hongik.demo_book.cateogory.service.Category;
import hongik.demo_book.member.entity.Member;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> findCategoriesWithMember(Member member);
}
