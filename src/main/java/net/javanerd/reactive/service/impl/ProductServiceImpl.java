package net.javanerd.reactive.service.impl;

import net.javanerd.reactive.dto.ProductDto;
import net.javanerd.reactive.entity.Product;
import net.javanerd.reactive.repository.ProductRepository;
import net.javanerd.reactive.service.ProductService;
import net.javanerd.reactive.utils.ProductMapper;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<ProductDto> getProducts() {
        return productRepository.findAll().map(ProductMapper::mapToProductDto);
    }

    @Override
    public Mono<ProductDto> getProduct(String id) {
        return productRepository.findById(id).map(ProductMapper::mapToProductDto);
    }

    @Override
    public Flux<ProductDto> getProductInRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min,max));
    }

    @Override
    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(ProductMapper::mapToProduct)
                .flatMap(productRepository::insert)
                .map(ProductMapper::mapToProductDto);

    }

    @Override
    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return productRepository.findById(id)
                .flatMap(product -> productDtoMono.map(ProductMapper::mapToProduct))
                .doOnNext(e -> e.setId(id))
                .flatMap(productRepository::save)
                .map(ProductMapper::mapToProductDto);
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }
}
