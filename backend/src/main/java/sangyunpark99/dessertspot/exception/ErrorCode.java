package sangyunpark99.dessertspot.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    REQUEST_VALUE_INVALID(HttpStatus.BAD_REQUEST, "CM-001", "요청한 값이 올바르지 않습니다."),

    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "USER-001", "이미 존재 하는 사용자 입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER-002", "존재 하지 않는 사용자 입니다."),
    USER_INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "USER-003", "유효 하지 않은 비밀 번호 입니다."),

    INSUFFICIENT_PERMISSIONS(HttpStatus.FORBIDDEN, "AUTH-001", "유효 하지 않은 접근 권한입니다."),

    INVALID_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED, "JWT-001", "유효 하지 않은 서명 입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "JWT-002", "만료된 토큰 입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "JWT-003", "지원 되지 않는 토큰 입니다."),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "JWT-004", "유효 하지 않은 토큰 입니다."),
    EMPTY_JWT_TOKEN_HEADER(HttpStatus.UNAUTHORIZED, "JWT-005", "헤더에 토큰이 존재 하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
