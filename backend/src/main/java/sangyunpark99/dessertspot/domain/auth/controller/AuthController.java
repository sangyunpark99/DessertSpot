package sangyunpark99.dessertspot.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sangyunpark99.dessertspot.domain.auth.dto.login.LoginRequest;
import sangyunpark99.dessertspot.domain.auth.dto.login.LoginResponse;
import sangyunpark99.dessertspot.domain.auth.dto.signup.SignUpResponse;
import sangyunpark99.dessertspot.domain.auth.dto.signup.SignupRequest;
import sangyunpark99.dessertspot.domain.auth.service.AuthService;

@Slf4j
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody @Valid SignupRequest request) {
        SignUpResponse response = authService.signup(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok().body(response);
    }
}
