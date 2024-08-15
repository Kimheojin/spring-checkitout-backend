package hongik.demo_book.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    //주소 관련 DTO
    private String city;
    private String street;
    private String zipcode;

}
