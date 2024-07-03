package sangyunpark99.dessertspot.product.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sangyunpark99.dessertspot.config.JpaConfig;
import sangyunpark99.dessertspot.domain.product.entity.Kind;
import sangyunpark99.dessertspot.domain.product.entity.Product;
import sangyunpark99.dessertspot.domain.product.repository.ProductRepository;
import sangyunpark99.dessertspot.domain.user.entity.User;
import sangyunpark99.dessertspot.domain.user.repository.UserRepository;

@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@Import(JpaConfig.class)
public class ProductRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {

        // product id 초기화 -> 테스트 한번 수행하면 id가 auto_increment이므로 매번 테스트시작전 시작 id를 1로 초기화
        em.createNativeQuery("ALTER TABLE products ALTER COLUMN product_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE users ALTER COLUMN user_id RESTART WITH 1").executeUpdate();

        User user = new User("abc@abc.com", "abc123", "abc");
        userRepository.save(user);
        em.flush();
        em.clear();

        Product product1 = new Product(user, "디저트1", 1000L, "디저트1 입니다.", 1L, Kind.BREAD, "https://abc.com/");
        Product product2 = new Product(user, "디저트2", 2000L, "디저트2 입니다.", 2L, Kind.BREAD, "https://abc.com/");
        Product product3 = new Product(user, "디저트3", 3000L, "디저트3 입니다.", 3L, Kind.BREAD, "https://abc.com/");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        em.flush();
        em.clear();
    }

    @Test
    void 상품_조회하기_성공_4개상품에서_첫번째상품을_제외한_두번째세번째상품_가져오기() {

        //given
        Pageable pageable = PageRequest.of(0, 2); // 페이지 번호는 의미가 없다.
        Long cursor = 1L; // 아이디가 1보다 큰 항목중에서 2개의 항목을 가져온다.

        //when
        Optional<List<Product>> result = productRepository.findByCursor(cursor, pageable);

        //then
        List<Product> products = result.get();

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(result).isPresent();
            softAssertions.assertThat(products).hasSize(2);
            softAssertions.assertThat(products.get(0).getName()).isEqualTo("디저트2");
            softAssertions.assertThat(products.get(1).getName()).isEqualTo("디저트3");
        });
    }

    @Test
    void 상품_조회하기_성공_커서가_마지막_상품인경우_빈_상품_가져오기() throws Exception{
        //given
        Pageable pageable = PageRequest.of(0, 2); // 페이지 번호는 의미가 없다.
        Long cursor = 4L; // 아이디가 1보다 큰 항목중에서 2개의 항목을 가져온다.

        //when
        Optional<List<Product>> result = productRepository.findByCursor(cursor, pageable);

        //then
        List<Product> products = result.get();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(result).isPresent();
            softAssertions.assertThat(products).isEmpty();
        });
    }


}
