package sangyunpark99.dessertspot.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sangyunpark99.dessertspot.domain.product.dto.ProductDto;
import sangyunpark99.dessertspot.domain.product.entity.Product;
import sangyunpark99.dessertspot.domain.product.repository.ProductRepository;
import sangyunpark99.dessertspot.exception.CustomException;
import sangyunpark99.dessertspot.exception.ErrorCode;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getProducts(final Long cursor, int pageSize) { // 디저트 가져오기

        Pageable pageable = PageRequest.of(0, pageSize);
        List<Product> products = productRepository.findByCursor(cursor, pageable).orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        List<ProductDto> productsDto = products.stream().map(ProductDto::new).collect(Collectors.toList());

        return productsDto;
    }

    public Long getNextCursor(final Long cursor, int pageSize) {
        return cursor + pageSize;
    }
}
