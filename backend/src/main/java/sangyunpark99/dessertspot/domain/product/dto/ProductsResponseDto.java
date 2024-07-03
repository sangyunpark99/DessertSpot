package sangyunpark99.dessertspot.domain.product.dto;

import java.util.List;

public record ProductsResponseDto(List<ProductDto> products, Long nextCursor) {
}
