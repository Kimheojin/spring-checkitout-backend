package hongik.demo_book.domain;


import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROKE_ADMIN"), USER("ROLE_USER");

    UserRole(String value){
        this.value = value;
    }

    private String value;
}
