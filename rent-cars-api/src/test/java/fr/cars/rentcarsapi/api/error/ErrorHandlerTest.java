package fr.cars.rentcarsapi.api.error;

import fr.cars.rentcarsapi.dto.error.ErrorDto;
import fr.cars.rentcarsapi.exception.NotFoundRentalCarException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorHandlerTest {

    @Test
    void shouldHandleNotFoundRentalCarException() {
        String message = "je suis un test";

        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDto errorDto = errorHandler.handleNotFoundRentalCarException(new NotFoundRentalCarException(message));

        assertThat(errorDto.message()).isEqualTo(message);
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() {
        String message = "La requête envoyée est invalide";

        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDto errorDto = errorHandler.handleMethodArgumentNotValidException();

        assertThat(errorDto.message()).isEqualTo(message);
    }
}
