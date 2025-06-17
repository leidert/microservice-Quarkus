package com.tanos.demo.domain.gateway;

import com.tanos.demo.domain.model.Book;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface BookGateway {

    public Uni<List<Book>> findAll();
    public Uni<Book> insert(Book book);
    public Uni<Book> findById(Long id);
    public Uni<Boolean> delete(Long id);
    public Uni<Book> update(Book book);
}
