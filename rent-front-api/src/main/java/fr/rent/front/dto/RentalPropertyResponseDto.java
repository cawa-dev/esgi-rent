package fr.rent.front.dto;

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
