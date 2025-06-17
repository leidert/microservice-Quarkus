package com.tanos.demo.infrastructure.drivers_adapters;

import com.tanos.demo.domain.Exception.BookNotFoundException;
import com.tanos.demo.domain.gateway.BookGateway;
import com.tanos.demo.domain.model.Book;
import static com.tanos.demo.infrastructure.mapper.BookMapper.*;

import com.tanos.demo.infrastructure.mapper.BookMapper;
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
public class BookGatewayImp implements BookGateway {

    @Inject
    BookRepository repository;

    @Override
    @WithSession
    public Uni<List<Book>> findAll() {

        return repository.listAll()
                .onItem().transform(entities ->
                        entities.stream()
                                .map(BookMapper::toBook)
                                .collect(Collectors.toList()));

    }

    @Override
    public Uni<Book> insert(Book book) {
        var data = toEntity(book);
       return repository.persist(data).map(BookMapper::toBook);
    }

    @Override
    public Uni<Book> findById(Long id) {
        return repository.findById(id)
                .onItem().ifNull()
                .failWith(()-> new NotFoundException("no hay item con el id "+ id))
                .map(BookMapper::toBook);
    }

    @Override
    public Uni<Boolean> delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public Uni<Book> update(Book book) {
        return repository.findById(book.getId())
                .onItem().ifNull().failWith(() -> new BookNotFoundException("no hay item con el id "+ book.getId()))
                .onItem().transform(existingEntity -> {
                    // Actualizar los campos
                    existingEntity.setTitle(book.getTitle());
                    existingEntity.setDescription(book.getDescription());
                    existingEntity.setPubDate(book.getPubDate());
                    existingEntity.setNumPages(book.getNumPages());
                    return existingEntity;
                })
                .onItem().call(entity -> repository.flush())
                .map(BookMapper::toBook);
    }
}
