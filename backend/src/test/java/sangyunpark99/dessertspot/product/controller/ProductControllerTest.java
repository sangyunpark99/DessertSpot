package sangyunpark99.dessertspot.product.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sangyunpark99.dessertspot.exception.ErrorCode.REQUEST_VALUE_INVALID;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;
import sangyunpark99.dessertspot.domain.product.controller.ProductController;
import sangyunpark99.dessertspot.domain.product.dto.ProductDto;
import sangyunpark99.dessertspot.domain.product.entity.Kind;
import sangyunpark99.dessertspot.domain.product.entity.Product;
import sangyunpark99.dessertspot.domain.product.service.ProductService;
import sangyunpark99.dessertspot.domain.user.entity.User;
import sangyunpark99.dessertspot.exception.GlobalExceptionHandler;

@WebMvcTest(controllers = {ProductController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    ProductController productController;

    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;

    private static List<ProductDto> products;

    @BeforeAll
    static void initProducts() {
        products = new ArrayList<>();
        final User user = new User("abc@abc.com", "abc123", "abc");
        final Product product = new Product(user, "디저트", 1000L,"디저트 입니다.",1L, Kind.BREAD,"https://abc.com/");
        final ProductDto productDto = new ProductDto(product);
        products.add(productDto);
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .alwaysDo(print())
                .build();
    }


    @Test
    void 상품가져오기를_성공한다() throws Exception {
        //given

        //when
        when(productService.getProducts(anyLong(), anyInt())).thenReturn(products);
        when(productService.getNextCursor(anyLong(), anyInt())).thenReturn(2L);

        //then
        mockMvc.perform(get("/products?cursor=1&pageSize=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products[0].name").value("디저트"))
                .andExpect(jsonPath("$.nextCursor").value(2L));
    }


    @Test
    void 상품가져오기_잘못된_파라미터() throws Exception {
        mockMvc.perform(get("/products?cursor=invalid&pageSize=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(REQUEST_VALUE_INVALID.getMessage()));
    }

    @Test
    void 상품가져오기_필수_파라미터_커서_누락() throws Exception {
        mockMvc.perform(get("/products?pageSize=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(REQUEST_VALUE_INVALID.getMessage()));
    }
}
