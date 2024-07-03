package sangyunpark99.dessertspot.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String EXCEPTION_FORMAT = "%s : %s";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatusCode statusCode, WebRequest request) {

        logger.error(String.format(EXCEPTION_FORMAT, ex.getClass().getSimpleName()), ex);

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    /**
     * @RequestParam의 값 Type이 일치 하지 않는 경우 발생하는 에러
     */
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatusCode status, WebRequest request) {
        ErrorCode errorCode = ErrorCode.REQUEST_VALUE_INVALID;

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.toResponseEntity(errorCode));
    }

    /**
     * @RequestParam에 필요한 값이 존재하지 않는 경우 발생하는 에러
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatusCode status,
                                                                          WebRequest request) {
        ErrorCode errorCode = ErrorCode.REQUEST_VALUE_INVALID;

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.toResponseEntity(errorCode));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException e,
            final HttpHeaders headers,
            final HttpStatusCode status,
            final WebRequest request
    ) {

        ErrorCode errorCode = ErrorCode.REQUEST_VALUE_INVALID;

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.toResponseEntity(errorCode));
    } // @Valid Exception이 터지는 경우

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.toResponseEntity(e.getErrorCode()));
    }
}
