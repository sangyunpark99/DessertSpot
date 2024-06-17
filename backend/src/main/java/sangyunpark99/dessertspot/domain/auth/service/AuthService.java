package sangyunpark99.dessertspot.domain.auth.service;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangyunpark99.dessertspot.domain.auth.dto.login.LoginRequest;
import sangyunpark99.dessertspot.domain.auth.dto.login.LoginResponse;
import sangyunpark99.dessertspot.domain.auth.dto.signup.SignUpResponse;
import sangyunpark99.dessertspot.domain.auth.dto.signup.SignupRequest;
import sangyunpark99.dessertspot.domain.user.entity.Role;
import sangyunpark99.dessertspot.domain.user.entity.User;
import sangyunpark99.dessertspot.domain.user.repository.UserRepository;
import sangyunpark99.dessertspot.exception.CustomException;
import sangyunpark99.dessertspot.exception.ErrorCode;
import sangyunpark99.dessertspot.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class AuthService {

    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public SignUpResponse signup(SignupRequest request) {
        User user = User.builder()
                .email(request.email())
                .password(bCryptPasswordEncoder.encode(request.password()))
                .username(request.username())
                .build();

        if (userRepository.existsByEmail(request.email())) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
        }

        userRepository.save(user);

        HashMap<String, String> tokens = getTokens(request.username(), user.getEmail(), Role.USER.toString());

        return new SignUpResponse(tokens.get(ACCESS_TOKEN), tokens.get(REFRESH_TOKEN));
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!bCryptPasswordEncoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.USER_INVALID_PASSWORD);
        }

        HashMap<String, String> tokens = getTokens(user.getUsername(), user.getEmail(), Role.USER.toString());

        return new LoginResponse(tokens.get(ACCESS_TOKEN), tokens.get(REFRESH_TOKEN));
    }

    private HashMap<String, String> getTokens(final String username, final String email, final String role) {

        final String accessToken = jwtTokenProvider.createAccessToken(username, email, Role.USER.toString());
        final String refreshToken = jwtTokenProvider.createRefreshToken(username, email, Role.USER.toString());

        HashMap<String, String> tokens = new HashMap<>();
        tokens.put(ACCESS_TOKEN, accessToken);
        tokens.put(REFRESH_TOKEN, refreshToken);

        return tokens;
    }
}
