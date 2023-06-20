package fr.rent.front.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundRentalCarExceptionTest {

    @Test
    void shouldReturnCorrectMessage() {
        String errorMessage = "Rental car not found";
        NotFoundRentalCarException exception = new NotFoundRentalCarException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }
}
