package com.tanos.demo.infrastructura.drivers_adapters;

import com.tanos.demo.infrastructure.drivers_adapters.ProductGatewayImp;
import com.tanos.demo.infrastructure.drivers_adapters.ProductRepository;
import com.tanos.demo.infrastructure.mapper.ProductMapper;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductGatewayImpTest {

    ProductRepository repository;
    ProductGatewayImp gatewayImp;

    @BeforeEach
    void setup(){
        repository = mock(ProductRepository.class);
        gatewayImp = new ProductGatewayImp(repository);
    }

    @Test
    void findByIdTest(){
        var book = BookMother.getBook();
            when(repository.findById(1L)).thenReturn(Uni.createFrom().item(book).map(ProductMapper::toEntity));

            gatewayImp.findById(1L).subscribe().with(result->{
                assertEquals("don quijote", result.getTitle());
                assertEquals("test don quijote", result.getDescription());
                assertEquals(200, result.getPriceProduct());
                assertEquals(LocalDate.parse("2025-06-11"), result.getPubDate());
            });
        }

        @Test
        void findByIdNotFound(){
            when(repository.findById(1L)).thenReturn(Uni.createFrom().nullItem());
            gatewayImp.findById(1L).subscribe()
                    .withSubscriber(UniAssertSubscriber.create())
                    .assertFailedWith(NotFoundException.class, "no hay item con el id 1");
        }

        @Test
        void getAllTest(){
            var books = BookMother.gatAllBooks();
            var bookEntities = books.stream().map(ProductMapper::toEntity).collect(Collectors.toList());
            when(repository.listAll()).thenReturn(Uni.createFrom().item(bookEntities));

            gatewayImp.findAll().subscribe().with(result -> {
                assertNotNull(result);
                assertEquals(books.size(), result.size());
                assertEquals(books.get(0).getId(), result.get(0).getId());
            });

        }

        @Test
        void getAllNotFoundTest(){
            when(repository.listAll()).thenReturn(Uni.createFrom().nullItem());

            gatewayImp.findAll().subscribe()
                    .withSubscriber(UniAssertSubscriber.create())
                    .assertFailedWith(NullPointerException.class, "Cannot invoke \"java.util.List.stream()\" because \"entities\" is null");
        }

        @Test
        void insertTest(){

            var book = BookMother.insertBook();
            var bookEntity = ProductMapper.toEntity(book);

            when(repository.persist(bookEntity)).thenReturn(Uni.createFrom().item(book).map(ProductMapper::toEntity));

            gatewayImp.insert(book).subscribe().with(result ->{
                assertEquals(book.getTitle(),result.getTitle());
            });
        }

        void insertNotFondTest(){
            var book = BookMother.insertBook();
            var bookEntity = ProductMapper.toEntity(book);
            when(repository.persist(bookEntity)).thenReturn(Uni.createFrom().nullItem());
        }
}
