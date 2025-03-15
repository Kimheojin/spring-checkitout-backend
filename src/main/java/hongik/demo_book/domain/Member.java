package hongik.demo_book.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.*;

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

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Category> categories = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Library> librarys = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<MemberAuthority> memberAuthorities = new HashSet<>();


    //address update 관련
    public void updateAddress(Address address) {
        this.address = address;
    }
}
