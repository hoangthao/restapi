/*package com.example.restapi.product.rx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductRxService {

    @Autowired
    private ProductRxRepository repository;

    public Flux<ProductRx> getAllProducts(){
        return this.repository.findAll();
    }

    public Mono<ProductRx> getProductById(int productId){
        return this.repository.findById(productId);
    }

    public Mono<ProductRx> createProduct(final ProductRx product){
        return this.repository.save(product);
    }

    public Mono<ProductRx> updateProduct(int productId, final Mono<ProductRx> productMono){
        return this.repository.findById(productId)
                .flatMap(p -> productMono.map(u -> {
                    p.setDescription(u.getDescription());
                    p.setPrice(u.getPrice());
                    p.setRestock(u.getRestock());
                    return p;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    public Mono<Void> deleteProduct(final int id){
        return this.repository.deleteById(id);
    }

}*/