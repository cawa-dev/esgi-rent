package fr.cars.rentcarsapi.exception;

public class NotFoundRentalCarException extends RuntimeException {

    public NotFoundRentalCarException(String message) {
        super(message);
    }
}
