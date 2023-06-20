package fr.rent.front.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class InvalidRequestRentalCarExceptionTest {

    @Test
    void shouldReturnCorrectMessage() {
        String errorMessage = "Rental car not found";
        InvalidRequestRentalCarException exception = new InvalidRequestRentalCarException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }
}
