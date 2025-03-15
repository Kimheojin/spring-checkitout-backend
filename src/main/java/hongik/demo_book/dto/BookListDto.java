package hongik.demo_book.dto;


import hongik.demo_book.domain.CategoryName;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookListDto {

    //카테고리만 보내면 해당 유저에 그 북 리스트 반환 하면 될듯
    private CategoryName categoryName;
}
