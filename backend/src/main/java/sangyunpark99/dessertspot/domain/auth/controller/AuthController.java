package sangyunpark99.dessertspot.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sangyunpark99.dessertspot.domain.auth.dto.login.LoginRequestDto;
import sangyunpark99.dessertspot.domain.auth.dto.login.LoginResponseDto;
import sangyunpark99.dessertspot.domain.auth.dto.signup.SignUpResponseDto;
import sangyunpark99.dessertspot.domain.auth.dto.signup.SignupRequestDto;
import sangyunpark99.dessertspot.domain.auth.service.AuthService;

@Slf4j
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody @Valid SignupRequestDto request) {
        SignUpResponseDto response = authService.signup(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto request) {
        log.info("로그인 요청");
        LoginResponseDto response = authService.login(request);
        return ResponseEntity.ok().body(response);
    }
}
