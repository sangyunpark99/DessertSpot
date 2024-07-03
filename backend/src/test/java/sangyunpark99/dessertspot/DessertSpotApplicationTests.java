package sangyunpark99.dessertspot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sangyunpark99.dessertspot.jwt.JwtTokenProvider;

@SpringBootTest
class DessertSpotApplicationTests {

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @Test
    void contextLoads() {
    }

}
