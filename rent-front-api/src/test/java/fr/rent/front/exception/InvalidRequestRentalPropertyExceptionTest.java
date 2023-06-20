package fr.rent.front.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class InvalidRequestRentalPropertyExceptionTest {

    @Test
    void shouldReturnCorrectMessage() {
        String errorMessage = "Rental property not found";
        InvalidRequestRentalPropertyException exception = new InvalidRequestRentalPropertyException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }
}
