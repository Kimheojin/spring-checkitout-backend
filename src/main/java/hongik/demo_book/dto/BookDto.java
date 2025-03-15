package hongik.demo_book.dto;


import hongik.demo_book.domain.CategoryName;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String isbn13;
    //세가지 타입
    //Favorite, Recommendation, read
    private CategoryName categoryName;

}
