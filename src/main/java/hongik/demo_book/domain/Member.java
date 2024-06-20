package hongik.demo_book.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
//이메일, 가입날짜, 비밀번호 추가하기


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;




    private String name;



    @Column(unique = true)
    private String email;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date join_time;

    @Embedded
    private Address address;

//@JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Book> books = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    private List<Library> librarys = new ArrayList<>();


}
