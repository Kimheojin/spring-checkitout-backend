package hongik.demo_book.book.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import hongik.demo_book.book.entity.Book;
import hongik.demo_book.cateogory.service.CategoryName;
import hongik.demo_book.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Book> findBookByCategoryName(String categoryname, Member member) {
        return jpaQueryFactory.selectFrom(QBook.book)
                .join(QBook.book.category, QCategory.category)
                .where(QCategory.category.categoryName.eq(CategoryName.valueOf(categoryname))
                        .and(QCategory.category.member.eq(member)))
                .fetch();
    }
}
