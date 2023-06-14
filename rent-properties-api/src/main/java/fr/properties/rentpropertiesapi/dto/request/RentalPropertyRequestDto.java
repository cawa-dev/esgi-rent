package fr.properties.rentpropertiesapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RentalPropertyRequestDto(
        @NotNull String description,
        @NotNull String town,
        @NotNull String address,
        @NotNull String propertyType,
        @NotNull Double rentAmount,
        @NotNull Double securityDepositAmount,
        @NotNull Double area,
        int numberOfBedrooms,
        int floorNumber,
        int numberOfFloors,
        String constructionYear,
        String energyClassification,
        boolean hasElevator,
        boolean hasIntercom,
        boolean hasBalcony,
        boolean hasParkingSpace
) {
}
