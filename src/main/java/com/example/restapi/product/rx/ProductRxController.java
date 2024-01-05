/*package com.example.restapi.product.rx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@CrossOrigin(origins = "*", allowedHeaders = {"Authorization"}, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("products")
public class ProductRxController {

    @Autowired
    private ProductRxService productService;

    @GetMapping
    public Flux<ProductRx> getAll(){
        return this.productService.getAllProducts();
    }

    @GetMapping("{productId}")
    public Mono<ResponseEntity<ProductRx>> getProductById(@PathVariable int productId){
        return this.productService.getProductById(productId)
                                .map(ResponseEntity::ok)
                                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductRx> createProduct(@RequestBody Mono<ProductRx> productMono){
        return productMono.flatMap(this.productService::createProduct);
    }

    @PutMapping("{productId}")
    public Mono<ProductRx> updateProduct(@PathVariable int productId,
                                       @RequestBody Mono<ProductRx> productMono){
        return this.productService.updateProduct(productId, productMono);
    }

    @DeleteMapping("{productId}")
    public Mono<Void> deleteProduct(@PathVariable int productId){
        return this.productService.deleteProduct(productId);
    }

}*/