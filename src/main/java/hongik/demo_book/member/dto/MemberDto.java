package hongik.demo_book.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import hongik.demo_book.member.entity.AuthorityDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    @NotNull
    @Size(min = 1, max = 50)
    private String memberName;

    //객체를 직렬화시 포함 X
    //직렬과 시 포함 O
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    //닉네임 대신 email 사용
    //로그인 시 email로 바꾸기(아직 구현 X)
    @NotNull
    @Email
    @Size(min = 3, max = 50)
    private String email;

    private Set<AuthorityDto> authorityDtoSet;

    public static MemberDto from(MemberWithAuthoritiesDto memberWithAuthoritiesDto) {
        if(memberWithAuthoritiesDto == null) return null;

        return MemberDto.builder()
                .memberName(memberWithAuthoritiesDto.getMemberName())
                .email(memberWithAuthoritiesDto.getEmail())
                .authorityDtoSet(memberWithAuthoritiesDto.getAuthorities().stream()
                        .map(authority -> AuthorityDto
                                .builder()
                                .authorityName(authority)
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}