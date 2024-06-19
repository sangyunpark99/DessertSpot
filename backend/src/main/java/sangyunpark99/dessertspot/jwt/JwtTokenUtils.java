package sangyunpark99.dessertspot.jwt;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenUtils {

    private static final String AUTHORIZATION = "Authorization";
    private static final int BEARER_LENGTH = 7;

    public static String resolveToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION).substring(BEARER_LENGTH);
    }

}
