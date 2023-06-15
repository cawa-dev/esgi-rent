package fr.cars.rentcarsapi.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RentalCarResponseDto(
        @NotNull String brand,
        @NotNull String model,
        @NotNull @Min(1) Double rentAmount,
        @NotNull @Min(1) Double securityDepositAmount,
        @NotNull @Min(3) Integer numberOfSeats,
        @NotNull @Min(3) Integer numberOfDoors,
        @NotNull Boolean hasAirConditioning
) {
}
