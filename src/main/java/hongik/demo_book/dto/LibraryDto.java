package hongik.demo_book.dto;

import hongik.demo_book.domain.LibraryStatus;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDto {
    private String libraryCode;

    //Around, Favorite 두개
    private LibraryStatus libraryStatus;
}
