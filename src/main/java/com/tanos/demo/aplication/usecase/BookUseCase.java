package com.tanos.demo.aplication.usecase;

import com.tanos.demo.domain.gateway.BookGateway;
import com.tanos.demo.domain.model.Book;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BookUseCase {

    BookGateway bookGateway;

    @Inject
    public BookUseCase(BookGateway bookGateway){
        this.bookGateway = bookGateway;
    }

    public Uni<List<Book>> findAll() {
        return bookGateway.findAll();
    }

    @WithTransaction
    public Uni<Book> insert(Book book) {
        return bookGateway.insert(book);
    }


    @WithSession
    public Uni<Book> findById(Long id) {
        return bookGateway.findById(id);
    }

    @WithTransaction
    public Uni<Boolean> delete(Long id) {
       return bookGateway.delete(id);
    }

    @WithTransaction
    public Uni<Book> update(Book book){
        return bookGateway.update(book);
    }
}
