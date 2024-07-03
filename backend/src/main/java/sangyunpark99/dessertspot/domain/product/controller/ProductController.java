package sangyunpark99.dessertspot.domain.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sangyunpark99.dessertspot.domain.product.dto.ProductsResponseDto;
import sangyunpark99.dessertspot.domain.product.service.ProductService;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private static final String DEFAULT_SIZE = "4";

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductsResponseDto> getProducts(
           @Valid @RequestParam(value = "cursor") Long cursor,
           @Valid @RequestParam(value = "pageSize", defaultValue = DEFAULT_SIZE) int pageSize
    ) {

        final Long nextCursor = productService.getNextCursor(cursor,pageSize);
        final ProductsResponseDto response = new ProductsResponseDto(productService.getProducts(cursor,pageSize), nextCursor);

       return ResponseEntity.ok(response);
    }
}
