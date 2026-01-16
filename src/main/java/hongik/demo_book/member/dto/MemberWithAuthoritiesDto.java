package hongik.demo_book.member.dto;


import hongik.demo_book.address.entity.Address;
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

    // authorities 없는 생성자 추가
    public MemberWithAuthoritiesDto(Long id, String memberName, String email, Address address, boolean activated, String password) {
        this.id = id;
        this.memberName = memberName;
        this.email = email;
        this.address = address;
        this.activated = activated;
        this.password = password;
    }


}
