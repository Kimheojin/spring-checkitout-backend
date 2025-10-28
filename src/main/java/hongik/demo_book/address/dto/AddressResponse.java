package hongik.demo_book.address.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class AddressResponse {

    private String zipCode;
    private String street;
    private String city;
}
