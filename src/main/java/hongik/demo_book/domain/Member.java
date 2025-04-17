package hongik.demo_book.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
//이메일, 가입날짜, 비밀번호 추가하기

//pk
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name="membername", length = 50)
    private String membername;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(length = 100)
    private String password;

    @Embedded
    private Address address;

//활성화 여부
    @Column(name = "activated")
    private boolean activated;
    //address update 관련
    public void updateAddress(Address address) {
        this.address = address;
    }
}
