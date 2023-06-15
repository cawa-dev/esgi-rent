package fr.cars.rentcarsapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RentalCarRequestDto(
        @NotNull String brand,
        @NotNull String model,
        @NotNull @Min(1) Double rentAmount,
        @NotNull @Min(1) Double securityDepositAmount,
        Integer numberOfSeats,
        Integer numberOfDoors,
        Boolean hasAirConditioning
) {
}
