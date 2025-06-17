package com.tanos.demo.infrastructure.drivers_adapters;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepository implements PanacheRepositoryBase<BookEntity, Long> {
}
