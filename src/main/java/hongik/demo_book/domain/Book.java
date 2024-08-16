package hongik.demo_book.domain;


import jakarta.persistence.*;
import lombok.Builder;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String isbn13;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;



    //연관관계 메소드
    public void setCategory(Category category) {
        this.category = category;
        category.getBooks().add(this);
    }

    //생성 메소드
    public static Book createbook(Category category) {
        Book book = new Book();
        book.setCategory(category);
        return book;
    }


}
