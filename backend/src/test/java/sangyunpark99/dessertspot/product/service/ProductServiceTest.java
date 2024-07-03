package sangyunpark99.dessertspot.product.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sangyunpark99.dessertspot.domain.product.dto.ProductDto;
import sangyunpark99.dessertspot.domain.product.entity.Kind;
import sangyunpark99.dessertspot.domain.product.entity.Product;
import sangyunpark99.dessertspot.domain.product.repository.ProductRepository;
import sangyunpark99.dessertspot.domain.product.service.ProductService;
import sangyunpark99.dessertspot.domain.user.entity.User;
import sangyunpark99.dessertspot.exception.CustomException;
import sangyunpark99.dessertspot.exception.ErrorCode;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    private User user;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        user = new User("abc@abc.com", "abc123", "abc");
        product1 = new Product(user, "디저트1", 1000L, "디저트1 입니다.", 1L, Kind.BREAD, "https://abc.com/");
        product2 = new Product(user, "디저트2", 2000L, "디저트2 입니다.", 2L, Kind.BREAD, "https://abc.com/");
    }

    @Test
    void 상품_가져오기_성공() {
        // given
        Long cursor = 1L;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(0, pageSize);
        given(productRepository.findByCursor(cursor, pageable)).willReturn(Optional.of(Arrays.asList(product1, product2)));

        // when
        List<ProductDto> productsDto = productService.getProducts(cursor, pageSize);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(productsDto).hasSize(2);
            softAssertions. assertThat(productsDto.get(0).name()).isEqualTo("디저트1");
            softAssertions. assertThat(productsDto.get(1).name()).isEqualTo("디저트2");
        });
    }

    @Test
    void 상품_가져오기_실패_상품없음() {
        // given
        Long cursor = 10L;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(0, pageSize);
        given(productRepository.findByCursor(cursor, pageable)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> productService.getProducts(cursor, pageSize))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.PRODUCT_NOT_FOUND);
    }

    @Test
    void 다음커서_계산하기_성공() {
        // given
        Long cursor = 1L;
        int pageSize = 2;

        // when
        Long nextCursor = productService.getNextCursor(cursor, pageSize);

        // then
        assertThat(nextCursor).isEqualTo(3L);
    }
}
