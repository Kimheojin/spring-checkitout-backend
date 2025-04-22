package hongik.demo_book.Repository.customQueryRepository;

import hongik.demo_book.domain.Category;
import hongik.demo_book.domain.Member;
import hongik.demo_book.dto.repoDto.CategoryDto;
import hongik.demo_book.dto.repoDto.MemberWithAuthoritiesDto;

import java.util.List;
import java.util.Optional;

public interface CategoryReposiotryCustom {

    List<Category> findCategoriesWithMember(Member member);
}
