package hongik.demo_book.Repository.customQueryRepository;

import hongik.demo_book.dto.repoDto.MemberWithAuthoritiesDto;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<MemberWithAuthoritiesDto> findMemberWithAuthoritiesByEmail(String email);
}
