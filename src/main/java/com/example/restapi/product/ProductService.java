package com.example.restapi.product;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.restapi.utils.PageHelper;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final Scheduler scheduler;

    public Flux<Product> getAllProducts(){
        return Flux.defer(() -> Flux.fromIterable(repository.findAll()))
            .subscribeOn(scheduler);
    }

    public Mono<Product> getProductById(int productId){
        return Mono.fromCallable(() -> repository.findById(productId).orElseThrow())
            .subscribeOn(scheduler);
    }

    public Mono<Product> createProduct(final Product product){
        return Mono.fromCallable(() -> repository.save(product))
            .subscribeOn(scheduler);
    }

    public Mono<Product> updateProduct(int productId, final Product product){
        UnaryOperator<Product> updated = p -> {
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());
            p.setRestock(product.getRestock());
            return p;
        };
        return Mono.fromCallable(() -> repository.findById(productId)
                .map(updated::apply)
                .map(repository::save)
                .orElseThrow())
            .subscribeOn(scheduler);
    }

    public Mono<Void> deleteProduct(final int id){
        return Mono.<Void>fromRunnable(() -> repository.deleteById(id))
            .subscribeOn(scheduler);
    }

    public Mono<Page<Product>> fetchPaging(final ConditionDto condition) {
        final Specification<Product> spec = buildSpec(condition);
        final Pageable pageable = PageHelper.parsePageable(condition.getPage(), condition.getSize(), condition.getSorts());
        Mono<Long> count = Mono.fromCallable(() -> repository.count(spec)).subscribeOn(scheduler);
        Mono<List<Product>> products = Mono.fromCallable(() -> 
            repository.findAll(spec, pageable).stream().toList()).subscribeOn(scheduler);
        return count.zipWith(products).map(countAndProducts -> 
            new PageImpl<>(countAndProducts.getT2(), pageable, countAndProducts.getT1()));
    }

    private Specification<Product> buildSpec(final ConditionDto condition) {
        return (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (condition.getFrom().isPresent()) {
                predicates.add(cb.lessThanOrEqualTo(root.get("restock"), condition.getFrom().get()));
            } else {
                predicates.add(cb.lessThanOrEqualTo(root.get("restock"), LocalDateTime.now().with(LocalTime.MIN)));
            }
            condition.getTo().ifPresent(v -> predicates.add(cb.lessThanOrEqualTo(root.get("restock"), v)));
            condition.getDescription().ifPresent(v -> predicates.add(cb.like(root.get("description"), v)));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    
}
