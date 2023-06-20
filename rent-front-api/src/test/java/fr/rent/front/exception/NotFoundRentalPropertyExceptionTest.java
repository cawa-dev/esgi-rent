package fr.rent.front.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundRentalPropertyExceptionTest {

    @Test
    void shouldReturnCorrectMessage() {
        String errorMessage = "Rental property not found";
        NotFoundRentalPropertyException exception = new NotFoundRentalPropertyException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }
}
