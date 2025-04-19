package hongik.demo_book.domain;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
//member entity에 종속
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    private String city;
    private String street;
    //세부주소
    private String zipCode;

}

