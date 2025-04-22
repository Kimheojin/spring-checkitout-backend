package hongik.demo_book.Repository.customQueryRepository;

import hongik.demo_book.domain.Category;
import hongik.demo_book.domain.Member;
import hongik.demo_book.dto.repoDto.MemberWithAuthoritiesDto;

import java.util.Optional;

public interface CategoryReposiotryCustom {

    Optional<Category> findCategoriesWithMember(Member member);
}
