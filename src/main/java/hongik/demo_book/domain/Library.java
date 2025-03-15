package hongik.demo_book.domain;

import jakarta.persistence.*;
import lombok.*;

//즐겨찾기한 도서관 목록
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_id")
    private Long id;

    //도서관 코드
    private String library_code;

    @Enumerated(EnumType.STRING)
    private LibraryStatus library_status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getLibrarys().add(this);
    }

}
