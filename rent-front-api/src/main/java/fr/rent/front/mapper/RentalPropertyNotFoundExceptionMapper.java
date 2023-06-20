package fr.rent.front.mapper;

import fr.rent.front.exception.NotFoundRentalPropertyException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
public class RentalPropertyNotFoundExceptionMapper implements ExceptionMapper<NotFoundRentalPropertyException> {

    @Override
    public Response toResponse(NotFoundRentalPropertyException exception) {
        return Response.status(404)
                .entity(exception.getMessage())
                .type(APPLICATION_JSON)
                .build();
    }
}
