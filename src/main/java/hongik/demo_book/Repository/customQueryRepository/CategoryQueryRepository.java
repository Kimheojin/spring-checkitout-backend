package hongik.demo_book.Repository.customQueryRepository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import hongik.demo_book.domain.Category;
import hongik.demo_book.domain.Member;
import hongik.demo_book.domain.QCategory;
import hongik.demo_book.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CategoryQueryRepository implements CategoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Category> findCategoriesWithMember(Member member) {

        QMember qmember = QMember.member;
        QCategory category = QCategory.category;


        return jpaQueryFactory.select(QCategory.category)
                .from(category)
                .join(category.member, qmember)
                .where(qmember.id.eq(member.getId()))
                .fetch();
    }
}
