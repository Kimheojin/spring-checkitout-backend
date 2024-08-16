package hongik.demo_book.dto;

import hongik.demo_book.domain.LibraryStatus;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDto {
    private String library_code;

    //around, favorite 두개
    private LibraryStatus library_status;
}
