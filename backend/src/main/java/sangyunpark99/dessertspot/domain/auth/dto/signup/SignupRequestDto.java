package sangyunpark99.dessertspot.domain.auth.dto.signup;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupRequestDto(

        @NotEmpty(message = "이메일 입력은 필수 입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
        String email,

        @NotEmpty(message = "비밀 번호 입력은 필수 입니다.")
        @Size(min = 5, message = "비밀 번호는 최소 5자 이상 이어야 합니다.")
        String password,

        @NotEmpty(message = "사용자 이름 입력은 필수 입니다.")
        String username
    ) {}
