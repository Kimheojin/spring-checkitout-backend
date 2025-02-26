package hongik.demo_book.domain;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
//member entity에 종속
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String city;
    private String street;
    //세부주소
    private String zipcode;

}

