package fr.rent.front.mapper;


import fr.rent.front.exception.NotFoundRentalPropertyException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalPropertyNotFoundExceptionMapperTest {

    RentalPropertyNotFoundExceptionMapper rentalPropertyNotFoundExceptionMapper;

    @BeforeEach
    void setUp() {
        rentalPropertyNotFoundExceptionMapper = new RentalPropertyNotFoundExceptionMapper();
    }

    @Test
    void shouldMapRentalPropertyNotFoundExceptionToResponse() {
        // GIVEN
        NotFoundRentalPropertyException exception = new NotFoundRentalPropertyException("Rental property not found");

        // WHEN
        Response response = rentalPropertyNotFoundExceptionMapper.toResponse(exception);

        // THEN
        assertEquals(404, response.getStatus());
        assertEquals("application/json", response.getMediaType().toString());
        assertEquals("Rental property not found", response.getEntity());
    }
}
