package net.javanerd.reactive.controller;

import lombok.AllArgsConstructor;
import net.javanerd.reactive.dto.ProductDto;
import net.javanerd.reactive.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    ProductService productService;

    @GetMapping
    public Flux<ProductDto> getProducts() {
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable("id") String productId) {
        return productService.getProduct(productId);
    }
    @GetMapping("/product-range")
    public Flux<ProductDto> getProductInRange(
            @RequestParam("min") double min,
            @RequestParam("max") double max
    ) {
        return productService.getProductInRange(min,max);
    }
    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return productService.saveProduct(productDtoMono);
    }
    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable("id") String productId) {
        return productService.updateProduct(productDtoMono,productId);
    }
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") String productId) {
        return productService.deleteProduct(productId);
    }
}
