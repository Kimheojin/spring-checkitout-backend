package hongik.demo_book.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RcdRequestDto {
    @NotNull
    private String isbn13;
    @NotNull
    private String description;
}
