package fr.rent.front.dto.request;

public record RentalCarRequestDto(
        String brand,
        String model,
        Double rentAmount,
        Double securityDepositAmount,
        Integer numberOfSeats,
        Integer numberOfDoors,
        Boolean hasAirConditioning
) {
}
