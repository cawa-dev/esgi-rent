package fr.rent.front.exception;

public class InvalidRequestRentalCarException extends RuntimeException {

    public InvalidRequestRentalCarException(String message) {
        super(message);
    }
}
