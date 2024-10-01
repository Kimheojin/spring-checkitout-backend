package hongik.demo_book.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RcdReturnDto {
    private List<Long> similar_books;
}
