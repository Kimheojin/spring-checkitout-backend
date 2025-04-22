package hongik.demo_book.Repository.customQueryRepository;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hongik.demo_book.domain.QAuthority;
import hongik.demo_book.domain.QMember;
import hongik.demo_book.domain.QMemberAuthority;
import hongik.demo_book.dto.repoDto.MemberWithAuthoritiesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Optional<MemberWithAuthoritiesDto> findMemberWithAuthoritiesByEmail(String email){
        QMember member = QMember.member;
        QMemberAuthority memberAuthority = QMemberAuthority.memberAuthority;

        QAuthority authority = QAuthority.authority;

        MemberWithAuthoritiesDto result = queryFactory
                .select(Projections.constructor(MemberWithAuthoritiesDto.class, // 빌더 지원 x
                        member.id,
                        member.memberName,
                        member.email,
                        member.address,
                        member.activated,
                        member.password))
                .from(member)
                .where(member.email.eq(email))
                .fetchOne();

        if(result != null) {
            List<String> authorities = queryFactory
                    .select(authority.authorityName)
                    .from(memberAuthority)
                    .join(memberAuthority.authority, authority)
                    .where(memberAuthority.member.id.eq(result.getId()))
                    .fetch();

            result.setAuthorities(authorities);

            return Optional.of(result);
        }

        return Optional.empty();


    }
}
