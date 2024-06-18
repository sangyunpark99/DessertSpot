package sangyunpark99.dessertspot.auth.contorller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sangyunpark99.dessertspot.domain.auth.controller.AuthController;
import sangyunpark99.dessertspot.domain.auth.dto.login.LoginRequest;
import sangyunpark99.dessertspot.domain.auth.dto.login.LoginResponse;
import sangyunpark99.dessertspot.domain.auth.dto.signup.SignUpResponse;
import sangyunpark99.dessertspot.domain.auth.dto.signup.SignupRequest;
import sangyunpark99.dessertspot.domain.auth.service.AuthService;
import sangyunpark99.dessertspot.exception.GlobalExceptionHandler;

@WebMvcTest(controllers = {AuthController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class AuthControllerTest {

    @MockBean
    AuthService authService;

    @Autowired
    AuthController authController;

    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .alwaysDo(print())
                .build();
    }

    @Test
    void 회원가입을_성공한다() throws Exception {
        //given
        final SignupRequest request = new SignupRequest("abc@abc.com", "abcdef", "abc");
        final SignUpResponse response = new SignUpResponse("abcd", "efgh");

        //when
        when(authService.signup(any(SignupRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("abcd"))
                .andExpect(jsonPath("$.refreshToken").value("efgh"));
    }

    @Test
    void 이메일이_존재하지않아_회원가입을_실패한다() throws Exception {
        //given
        final SignupRequest request = new SignupRequest("", "abcdef", "abc");
        final SignUpResponse response = new SignUpResponse("abcd", "efgh");

        //whem
        when(authService.signup(any(SignupRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 이메일이_형식이_올바르지않아_회원가입을_실패한다() throws Exception {
        //given
        final SignupRequest request = new SignupRequest("abc.com", "abcdef", "abc");
        final SignUpResponse response = new SignUpResponse("abcd", "efgh");

        //when
        when(authService.signup(any(SignupRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 비밀번호_형식이_5글자보다적어_회원가입을_실패한다() throws Exception {
        //given
        final SignupRequest request = new SignupRequest("abc.com", "abc", "abc");
        final SignUpResponse response = new SignUpResponse("abcd", "efgh");

        //when
        when(authService.signup(any(SignupRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 비밀번호가_존재하지않아_회원가입을_실패한다() throws Exception {
        //given
        final SignupRequest request = new SignupRequest("abc@abc.com", "", "abc");
        final SignUpResponse response = new SignUpResponse("abcd", "efgh");

        //when
        when(authService.signup(any(SignupRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 유저이름이_존재하지않아_회원가입을_실패한다() throws Exception {
        //given
        final SignupRequest request = new SignupRequest("abc@abc.com", "abcdef", "");
        final SignUpResponse response = new SignUpResponse("abcd", "efgh");

        //when
        when(authService.signup(any(SignupRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 로그인을_성공한다() throws Exception {
        //given
        final LoginRequest request = new LoginRequest("abc@abc.com", "abcdef");
        final LoginResponse response = new LoginResponse("abcd", "efgh");

        //when
        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("abcd"))
                .andExpect(jsonPath("$.refreshToken").value("efgh"));
    }

    @Test
    void 이메일이_존재하지않아_로그인을_실패한다() throws Exception {
        //given
        final LoginRequest request = new LoginRequest("", "abcdef");
        final LoginResponse response = new LoginResponse("abcd", "efgh");

        //when
        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 비밀번호가_존재하지않아_로그인을_실패한다() throws Exception {
        //given
        final LoginRequest request = new LoginRequest("abc@abc.com", "");
        final LoginResponse response = new LoginResponse("abcd", "efgh");

        //when
        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 이메일이_형식이_올바르지않아_로그인을_실패한다() throws Exception {
        //given
        final LoginRequest request = new LoginRequest("abc.com", "abcdef");
        final LoginResponse response = new LoginResponse("abcd", "efgh");

        //when
        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 비밀번호_형식이_5글자보다적어_로그인을_실패한다() throws Exception {
        //given
        final LoginRequest request = new LoginRequest("abc@abc.com", "abc");
        final LoginResponse response = new LoginResponse("abcd", "efgh");

        //when
        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        //then
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


}
