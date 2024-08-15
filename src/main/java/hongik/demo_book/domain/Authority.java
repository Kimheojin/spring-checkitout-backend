package hongik.demo_book.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "authority")
@Getter
@Setter
@Builder
@AllArgsConstructor//모든 필드값을 생성자에서 받아들임
@NoArgsConstructor//파리미터가 없는 디폴트 생성자
public class Authority {

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;
}
