package hongik.demo_book.member.repository;

import hongik.demo_book.member.dto.MemberWithAuthoritiesDto;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<MemberWithAuthoritiesDto> findMemberWithAuthoritiesByEmail(String email);
}
