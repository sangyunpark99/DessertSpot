package sanyunpark99.dessertspot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberDto> findMember(@PathVariable("id") Long id) {
        return ResponseEntity.ok(memberService.findMemberById(id));
    }
}
