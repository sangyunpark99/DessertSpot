package sangyunpark99.dessertspot.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.ServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sangyunpark99.dessertspot.exception.ErrorCode;

@Component
public class JwtTokenProvider {

    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    private static final String EXCEPTION = "exception";

    @Value("${accessToken-expired}")
    private long ACCESS_EXPIRED_TIME;

    @Value("${refreshToken-expired}")
    private long REFRESH_EXPIRED_TIME;

    private SecretKey secretKey;

    public JwtTokenProvider(@Value("${secret}") String secret) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String createAccessToken(final String username, final String email, final String role) {
        return createToken(username, email, role, ACCESS_EXPIRED_TIME);
    }

    public String createRefreshToken(final String username, final String email, final String role) {
        return createToken(username, email, role , REFRESH_EXPIRED_TIME);
    }

    private String createToken(final String username, final String email, final String role, final long expirationTime) {
        Claims claims = Jwts.claims()
                .add(USERNAME, username)
                .add(EMAIL, email)
                .add(ROLE, role)
                .build();

        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(final String token, final ServletRequest request) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException ex) {
            // 잘못된 JWT 서명
            request.setAttribute(EXCEPTION, ErrorCode.INVALID_JWT_SIGNATURE);
        } catch (ExpiredJwtException ex) {
            // 만료된 JWT 토큰
            request.setAttribute(EXCEPTION, ErrorCode.EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException ex) {
            // 지원되지 않는 JWT 토큰
            request.setAttribute(EXCEPTION, ErrorCode.UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException ex) {
            // 잘못된 JWT 토큰
            request.setAttribute(EXCEPTION, ErrorCode.INVALID_JWT_TOKEN);
        }

        return false;
    }

    private Claims getClaimsFromToken(final String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmailFromToken(final String token) {
        return getClaimsFromToken(token).get(EMAIL,String.class);
    }
}
