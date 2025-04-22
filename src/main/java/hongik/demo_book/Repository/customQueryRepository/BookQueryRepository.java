package hongik.demo_book.Repository.customQueryRepository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import hongik.demo_book.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookQueryRepository implements BookRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Book> findBookBycategoryname(String categoryname, Member member) {
        return jpaQueryFactory.selectFrom(QBook.book)
                .join(QBook.book.category, QCategory.category)
                .where(QCategory.category.categoryName.eq(CategoryName.valueOf(categoryname))
                        .and(QCategory.category.member.eq(member)))
                .fetch();
    }
}
