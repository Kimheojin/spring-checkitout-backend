package hongik.demo_book.domain;


//즐겨찾기, 추천도서, 대출 도서목록


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {

    //name -> enum 으로 바꿔서 추가하기

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();

    //연관관계 메소드
    public void setMember(Member member) {
        this.member = member;
        member.getCategories().add(this);
    }

    //생성 메소드
    public static Category createcategory(Member member) {
        Category category = new Category();
        category.setMember(member);
        return category;
    }


}
