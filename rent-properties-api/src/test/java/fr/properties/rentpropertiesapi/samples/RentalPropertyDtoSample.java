package fr.properties.rentpropertiesapi.samples;

import java.util.List;

import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;

public class RentalPropertyDtoSample {

    public static RentalPropertyResponseDto oneRentalPropertyResponse() {
        return RentalPropertyResponseDto.builder()
                .address("77 Rue des roses")
                .area(37.48)
                .description("Appartement spacieux avec vue sur l'ESGI")
                .propertyType("Appartement")
                .rentAmount(750.90)
                .securityDepositAmount(1200.90)
                .town("Paris")
                .build();
    }

    public static List<RentalPropertyResponseDto> rentalPropertyResponseList() {
        RentalPropertyResponseDto rentalProperty = oneRentalPropertyResponse();

        return List.of(rentalProperty);
    }
}
