package com.tanos.demo.infrastructure.entry_points;

import com.tanos.demo.aplication.usecase.BookUseCase;
import com.tanos.demo.domain.model.Book;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/books")
@Transactional
@NonBlocking
public class BookResource {

    @Inject
    private BookUseCase bookUseCase;

    @GET
    public Uni<List<Book>> index(){
        return bookUseCase.findAll();
    }

    @POST
    public Uni<Book> insert(Book book){
        return bookUseCase.insert(book);

    }

    @GET
    @Path("{id}")
    public Uni<Book> retrieve(@PathParam("id") Long id) {
        return bookUseCase.findById(id);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
        bookUseCase.delete(id);
        return Response.ok().build();

    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, Book book){
        book.setId(id);
        return bookUseCase.update(book)
                .map(update -> Response.ok(update).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND)::build);
    }

}
