package fr.rent.front.dto.response;

public record RentalPropertyResponseDto(
        String address,
        double area,
        String description,
        String propertyType,
        double rentAmount,
        double securityDepositAmount,
        String town
) {
}
