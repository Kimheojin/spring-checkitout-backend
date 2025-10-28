package hongik.demo_book.member.entity;


import hongik.demo_book.address.entity.Address;
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


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name="member_name", length = 50)
    private String memberName;

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
