package hongik.demo_book.book.dto;


import hongik.demo_book.cateogory.service.CategoryName;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String isbn13;
    //세가지 타입
    //FAVORITE, RECOMMENDATION, READ
    private CategoryName categoryName;

}
