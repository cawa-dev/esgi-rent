package fr.properties.rentpropertiesapi.dto.request.patch;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record RentalPropertyRequestDtoPatch(
        @NonNull @Min(1) Double rentAmount
) {
}
