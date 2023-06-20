package fr.rent.front.dto.response;

public record RentalCarResponseDto(
        String brand,
        String model,
        Double rentAmount,
        Double securityDepositAmount,
        Integer numberOfSeats,
        Integer numberOfDoors,
        Boolean hasAirConditioning
) {
}
