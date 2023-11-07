package net.javanerd.reactive.service;

import net.javanerd.reactive.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<ProductDto> getProducts();
    Mono<ProductDto> getProduct(String id);
    Flux<ProductDto> getProductInRange(double min, double max);
    Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono);
    Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id);
    Mono<Void> deleteProduct(String id);
}
