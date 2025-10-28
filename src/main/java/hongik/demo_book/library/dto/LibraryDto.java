package hongik.demo_book.library.dto;

import hongik.demo_book.library.entity.LibraryStatus;
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
