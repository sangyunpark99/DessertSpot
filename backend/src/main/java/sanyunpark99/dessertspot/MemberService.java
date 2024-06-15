package sanyunpark99.dessertspot;

import org.springframework.stereotype.Service;

@Service
public class MemberService {

    public MemberDto findMemberById(Long id) {
        return new MemberDto(1L, "A", "hello@gmail.com");
    }
}
