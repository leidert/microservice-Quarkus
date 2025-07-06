package com.tanos.demo.domain.gateway;

import com.tanos.demo.domain.model.Product;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface ProductGateway {

    public Uni<List<Product>> findAll();
    public Uni<Product> insert(Product product);
    public Uni<Product> findById(Long id);
    public Uni<Boolean> delete(Long id);
    public Uni<Product> update(Product product);
}
