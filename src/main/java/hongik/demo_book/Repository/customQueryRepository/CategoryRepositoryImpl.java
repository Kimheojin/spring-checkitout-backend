package hongik.demo_book.Repository.customQueryRepository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import hongik.demo_book.domain.Category;
import hongik.demo_book.domain.Member;
import hongik.demo_book.domain.QCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Category> findCategoriesWithMember(Member member) {
        QCategory category = QCategory.category;

        return jpaQueryFactory
                .selectFrom(category)
                .where(category.member.eq(member))
                .fetch();
    }
}
