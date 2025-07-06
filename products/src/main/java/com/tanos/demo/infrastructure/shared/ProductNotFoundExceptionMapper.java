package com.tanos.demo.infrastructure.shared;

import com.tanos.demo.domain.Exception.ProductNotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException> {

    @Override
    public Response toResponse(ProductNotFoundException e) {
        var errorResponse = new ErrorResponse("Book Not Found",e.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
