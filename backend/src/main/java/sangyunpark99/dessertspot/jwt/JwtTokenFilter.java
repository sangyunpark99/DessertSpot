package sangyunpark99.dessertspot.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import sangyunpark99.dessertspot.exception.ErrorCode;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String EXCEPTION = "exception";

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            final String token = JwtTokenUtils.resolveToken(request);

            if(jwtTokenProvider.validateToken(token, request)) {
                    Authentication authentication = getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

        } catch (Exception e) {
            request.setAttribute(EXCEPTION, ErrorCode.INVALID_JWT_TOKEN);
        }

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(final String token) {
        String email = jwtTokenProvider.getEmailFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
