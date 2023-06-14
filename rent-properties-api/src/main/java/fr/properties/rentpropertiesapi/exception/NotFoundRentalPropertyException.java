package fr.properties.rentpropertiesapi.exception;

public class NotFoundRentalPropertyException extends RuntimeException {

    public NotFoundRentalPropertyException(String message) {
        super(message);
    }
}
