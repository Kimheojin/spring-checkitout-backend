package hongik.demo_book.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RcdReturnDto {
    private List<Long> similarBooks;
}
