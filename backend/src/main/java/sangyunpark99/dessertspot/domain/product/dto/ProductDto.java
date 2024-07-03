package sangyunpark99.dessertspot.domain.product.dto;

import sangyunpark99.dessertspot.domain.product.entity.Product;

public record ProductDto(String userName, String name, Long price, String description, Long stock, String imageUrl, String type) {

    public ProductDto(Product product) {
        this(
                product.getUser().getUsername(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getStock(),
                product.getImageUrl(),
                product.getType().toString());
    }
}
