package net.javanerd.reactive.utils;

import net.javanerd.reactive.dto.ProductDto;
import net.javanerd.reactive.entity.Product;
import org.springframework.beans.BeanUtils;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }

    public static Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }
}
