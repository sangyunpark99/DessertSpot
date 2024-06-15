package sanyunpark99.dessertspot;

import lombok.Getter;

@Getter
public class MemberDto {
    private Long id;
    private String name;
    private String email;

    public MemberDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
