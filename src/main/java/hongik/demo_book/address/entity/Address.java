package hongik.demo_book.address.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    private String city;
    private String street;
    //세부주소
    @Column(name = "zip_code")
    private String zipCode;

}

