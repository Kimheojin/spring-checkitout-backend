package hongik.demo_book.domain;


import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
//member entity에 종속
@Getter
public class Address {

    private String city;
    private String street;
    //세부주소
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;//우편번호
    }
}

