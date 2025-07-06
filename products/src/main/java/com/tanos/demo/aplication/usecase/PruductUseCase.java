package com.tanos.demo.aplication.usecase;

import com.tanos.demo.domain.gateway.ProductGateway;
import com.tanos.demo.domain.model.Product;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PruductUseCase {

    ProductGateway productGateway;

    @Inject
    public PruductUseCase(ProductGateway productGateway){
        this.productGateway = productGateway;
    }

    public Uni<List<Product>> findAll() {
        return productGateway.findAll();
    }

    @WithTransaction
    public Uni<Product> insert(Product product) {
        return productGateway.insert(product);
    }


    @WithSession
    public Uni<Product> findById(Long id) {
        return productGateway.findById(id);
    }

    @WithTransaction
    public Uni<Boolean> delete(Long id) {
       return productGateway.delete(id);
    }

    @WithTransaction
    public Uni<Product> update(Product product){
        return productGateway.update(product);
    }
}
