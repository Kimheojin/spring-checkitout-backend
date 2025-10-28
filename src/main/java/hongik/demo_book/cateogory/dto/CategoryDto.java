package hongik.demo_book.cateogory.dto;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private String categoryName;
    private String email;
}
