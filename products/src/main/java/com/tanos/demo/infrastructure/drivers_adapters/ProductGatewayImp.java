package com.tanos.demo.infrastructure.drivers_adapters;

import com.tanos.demo.domain.Exception.ProductNotFoundException;
import com.tanos.demo.domain.gateway.ProductGateway;
import com.tanos.demo.domain.model.Product;
import static com.tanos.demo.infrastructure.mapper.ProductMapper.*;

import com.tanos.demo.infrastructure.mapper.ProductMapper;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ApplicationScoped
public class ProductGatewayImp implements ProductGateway {

    @Inject
    ProductRepository repository;

    @Override
    @WithSession
    public Uni<List<Product>> findAll() {

        return repository.listAll()
                .onItem().transform(entities ->
                        entities.stream()
                                .map(ProductMapper::toProduct)
                                .collect(Collectors.toList()));

    }

    @Override
    public Uni<Product> insert(Product product) {
        var data = toEntity(product);
       return repository.persist(data).map(ProductMapper::toProduct);
    }

    @Override
    public Uni<Product> findById(Long id) {
        return repository.findById(id)
                .onItem().ifNull()
                .failWith(()-> new NotFoundException("no hay item con el id "+ id))
                .map(ProductMapper::toProduct);
    }

    @Override
    public Uni<Boolean> delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public Uni<Product> update(Product product) {
        return repository.findById(product.getId())
                .onItem().ifNull().failWith(() -> new ProductNotFoundException("no hay item con el id "+ product.getId()))
                .onItem().transform(existingEntity -> {
                    // Actualizar los campos
                    existingEntity.setTitle(product.getTitle());
                    existingEntity.setDescription(product.getDescription());
                    existingEntity.setPubDate(product.getPubDate());
                    existingEntity.setNumPages(product.getPriceProduct());
                    return existingEntity;
                })
                .onItem().call(entity -> repository.flush())
                .map(ProductMapper::toProduct);
    }
}
