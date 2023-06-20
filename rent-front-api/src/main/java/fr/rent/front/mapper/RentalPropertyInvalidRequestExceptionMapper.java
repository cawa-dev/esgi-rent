package fr.rent.front.mapper;

import fr.rent.front.exception.InvalidRequestRentalPropertyException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class RentalPropertyInvalidRequestExceptionMapper implements ExceptionMapper<InvalidRequestRentalPropertyException> {

    @Override
    public Response toResponse(InvalidRequestRentalPropertyException exception) {
        return Response.status(400)
                .entity(exception.getMessage())
                .type(APPLICATION_JSON)
                .build();
    }
}
