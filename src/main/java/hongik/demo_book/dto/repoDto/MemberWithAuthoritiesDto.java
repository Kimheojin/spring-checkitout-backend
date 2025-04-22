package hongik.demo_book.dto.repoDto;


import hongik.demo_book.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MemberWithAuthoritiesDto {


    private Long id;
    private String memberName;
    private String email;
    private Address address;
    private boolean activated;
    private String password;
    private List<String> authorities;

    // 명시적
    public MemberWithAuthoritiesDto(Long id, String memberName, String email, Address address, boolean activated, String password) {
        this.id = id;
        this.memberName = memberName;
        this.email = email;
        this.address = address;
        this.activated = activated;
        this.password = password;
        this.authorities = null; // 나중에 별도로 설정
    }


}
