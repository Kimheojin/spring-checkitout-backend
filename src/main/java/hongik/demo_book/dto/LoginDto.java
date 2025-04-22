package hongik.demo_book.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


//나중에 수정해야할듯
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    //이메일, password

    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 3, max = 100)
    private String password;
}

