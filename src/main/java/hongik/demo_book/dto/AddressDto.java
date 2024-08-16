package hongik.demo_book.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    //주소 관련 DTO
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String zipcode;

}
