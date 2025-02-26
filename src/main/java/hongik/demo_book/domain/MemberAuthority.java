package hongik.demo_book.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder @AllArgsConstructor
@Table(name = "member_authority")
public class MemberAuthority {

    // member 양방향, authority 단방향
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "authority_name")
    private Authority authority;
}
