package sangyunpark99.dessertspot.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sangyunpark99.dessertspot.domain.auth.dto.login.LoginRequestDto;
import sangyunpark99.dessertspot.domain.auth.dto.login.LoginResponseDto;
import sangyunpark99.dessertspot.domain.auth.dto.signup.SignUpResponseDto;
import sangyunpark99.dessertspot.domain.auth.dto.signup.SignupRequestDto;
import sangyunpark99.dessertspot.domain.auth.service.AuthService;
import sangyunpark99.dessertspot.domain.user.entity.User;
import sangyunpark99.dessertspot.domain.user.repository.UserRepository;
import sangyunpark99.dessertspot.exception.CustomException;
import sangyunpark99.dessertspot.exception.ErrorCode;
import sangyunpark99.dessertspot.jwt.JwtTokenProvider;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class AuthServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    AuthService authService;

    @Test
    void 회원_등록_성공() {
        // given
        final SignupRequestDto signupRequestDto = new SignupRequestDto("abc@abc.com", "abcdef", "abc");
        final User user = User.builder()
                .email(signupRequestDto.email())
                .password(signupRequestDto.password())
                .username(signupRequestDto.username())
                .build();

        final String accessToken = "accessToken";
        final String refreshToken = "refreshToken";

        // when
        when(userRepository.existsByEmail(signupRequestDto.email())).thenReturn(false);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtTokenProvider.createAccessToken(anyString(), anyString(), anyString())).thenReturn("accessToken");
        when(jwtTokenProvider.createRefreshToken(anyString(), anyString(), anyString())).thenReturn("refreshToken");

        SignUpResponseDto signUpResponseDto = authService.signup(signupRequestDto);

        // then
        assertThat(signUpResponseDto.accessToken()).isEqualTo(accessToken);
        assertThat(signUpResponseDto.refreshToken()).isEqualTo(refreshToken);
    }

    @Test
    void 회원_등록_실패_이미_존재_하는_유저() {
        // given
        final SignupRequestDto signupRequestDto = new SignupRequestDto("abc@abc.com", "abcdef", "abc");

        // when
        when(userRepository.existsByEmail(signupRequestDto.email())).thenReturn(true);

        // then
        assertThatThrownBy(() -> authService.signup(signupRequestDto))
                .isInstanceOf(CustomException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.USER_ALREADY_EXIST);
    }

    @Test
    void 로그인_성공() {
        // given
        final LoginRequestDto loginRequestDto = new LoginRequestDto("abc@abc.com","abcdef");
        final User user = User.builder()
                .email(loginRequestDto.email())
                .password(loginRequestDto.password())
                .username("abc")
                .build();

        final String accessToken = "accessToken";
        final String refreshToken = "refreshToken";

        // when
        when(userRepository.findByEmail(loginRequestDto.email())).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtTokenProvider.createAccessToken(anyString(), anyString(), anyString())).thenReturn("accessToken");
        when(jwtTokenProvider.createRefreshToken(anyString(), anyString(), anyString())).thenReturn("refreshToken");


        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);

        // then
        assertThat(loginResponseDto.accessToken()).isEqualTo(accessToken);
        assertThat(loginResponseDto.refreshToken()).isEqualTo(refreshToken);
    }

    @Test
    void 로그인_실패_존재_하지_않는_유저() {
        // given
        final LoginRequestDto loginRequestDto = new LoginRequestDto("abc@abc.com", "abcdef");

        // when
        when(userRepository.findByEmail(loginRequestDto.email())).thenThrow(new CustomException(ErrorCode.USER_NOT_FOUND));

        // then
        assertThatThrownBy(() -> authService.login(loginRequestDto))
                .isInstanceOf(CustomException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.USER_NOT_FOUND);
    }

    @Test
    void 로그인_실패_비밀번호_불일치() {
        // given
        final LoginRequestDto loginRequestDto = new LoginRequestDto("abc@abc.com", "abcdef");
        final User user = User.builder()
                .email(loginRequestDto.email())
                .password(loginRequestDto.password())
                .username("abc")
                .build();

        // when
        when(userRepository.findByEmail(loginRequestDto.email())).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(loginRequestDto.password(), "abcdef")).thenReturn(false);

        // then
        assertThatThrownBy(() -> authService.login(loginRequestDto))
                .isInstanceOf(CustomException.class)
                .extracting("errorCode")
                .isEqualTo(ErrorCode.USER_INVALID_PASSWORD);
    }
}
