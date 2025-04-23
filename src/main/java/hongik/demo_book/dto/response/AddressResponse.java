package hongik.demo_book.dto.response;


import lombok.*;
import org.springframework.stereotype.Repository;

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
