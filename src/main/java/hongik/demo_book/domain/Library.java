package hongik.demo_book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//즐겨찾기한 도서관 목록
@Entity
@Getter @Setter
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue
    @Column(name = "library_id")
    private Long id;

    //도서관 코드
    private String library_code;

    @Enumerated(EnumType.STRING)
    private LibraryStatus book_status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getLibrarys().add(this);
    }

    public static Library createlibrary(Member member) {
        Library library = new Library();
        library.setMember(member);
        return library;
    }

}
