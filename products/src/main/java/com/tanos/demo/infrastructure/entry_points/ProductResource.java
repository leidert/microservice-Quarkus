package com.tanos.demo.infrastructure.entry_points;

import com.tanos.demo.aplication.usecase.PruductUseCase;
import com.tanos.demo.domain.model.Product;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/product")
@Transactional
@NonBlocking
public class ProductResource {

    @Inject
    private PruductUseCase pruductUseCase;

    @GET
    public Uni<List<Product>> index(){
        return pruductUseCase.findAll();
    }

    @POST
    public Uni<Product> insert(Product product){
        return pruductUseCase.insert(product);

    }

    @GET
    @Path("{id}")
    public Uni<Product> retrieve(@PathParam("id") Long id) {
        return pruductUseCase.findById(id);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
        pruductUseCase.delete(id);
        return Response.ok().build();

    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, Product product){
        product.setId(id);
        return pruductUseCase.update(product)
                .map(update -> Response.ok(update).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND)::build);
    }

}
