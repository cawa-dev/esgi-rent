package fr.properties.rentpropertiesapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RentalPropertyRequestDto(
        @NotNull String description,
        @NotNull String town,
        @NotNull String address,
        @NotNull String propertyType,
        @NotNull @Min(1) Double rentAmount,
        @NotNull @Min(1) Double securityDepositAmount,
        @NotNull @Min(1) Double area,
        int numberOfBedrooms,
        int floorNumber,
        int numberOfFloors,
        @Min(1990) String constructionYear,
        String energyClassification,
        boolean hasElevator,
        boolean hasIntercom,
        boolean hasBalcony,
        boolean hasParkingSpace
) {
}
