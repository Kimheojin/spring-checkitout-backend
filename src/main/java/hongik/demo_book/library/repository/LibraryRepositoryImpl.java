package hongik.demo_book.library.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import hongik.demo_book.library.entity.Library;
import hongik.demo_book.member.entity.Member;
import hongik.demo_book.domain.QLibrary;
import hongik.demo_book.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LibraryRepositoryImpl implements LibraryRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Library> FindLibrayhWithMember(Member member) {
        return jpaQueryFactory.selectFrom(QLibrary.library)
                .join(QLibrary.library.member, QMember.member)
                .where(QLibrary.library.member.eq(member))
                .fetch();
    }
}
