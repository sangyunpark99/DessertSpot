package sangyunpark99.dessertspot.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private String name;
    private String code;
    private String message;

    public static ErrorResponse toResponseEntity(ErrorCode e){
        return ErrorResponse.builder()
                        .name(e.name())
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build();
    }
}

