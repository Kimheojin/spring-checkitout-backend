package hongik.demo_book.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Getter @Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    private String isbn13;

    @ManyToMany(mappedBy = "books")
    private List<Category> categories = new ArrayList<>();



    //연관관계 메소드
    public void setMember(Member member) {
        this.member = member;
        member.getBooks().add(this);
    }

    //생성 메소드
    public static Book createbook(Member member) {
        Book book = new Book();
        book.setMember(member);
        return book;
    }


}
