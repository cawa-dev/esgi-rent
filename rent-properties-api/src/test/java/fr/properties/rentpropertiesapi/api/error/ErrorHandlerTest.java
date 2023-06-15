package fr.properties.rentpropertiesapi.api.error;

import fr.properties.rentpropertiesapi.dto.error.ErrorDto;
import fr.properties.rentpropertiesapi.exception.NotFoundRentalPropertyException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorHandlerTest {

    @Test
    void shouldHandleNotFoundRentalPropertyException() {
        String message = "je suis un test";

        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDto errorDto = errorHandler.handleNotFoundRentalPropertyException(new NotFoundRentalPropertyException(message));

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
