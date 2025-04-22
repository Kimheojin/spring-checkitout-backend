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
    private List<String> authorities;
    private String password;

}
