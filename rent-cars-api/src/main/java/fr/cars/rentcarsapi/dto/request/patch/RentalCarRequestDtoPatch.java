package fr.cars.rentcarsapi.dto.request.patch;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record RentalCarRequestDtoPatch(
        @NonNull @Min(1) Double rentAmount
) {
}
