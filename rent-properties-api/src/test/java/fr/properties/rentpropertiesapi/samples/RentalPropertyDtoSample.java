package fr.properties.rentpropertiesapi.samples;

import fr.properties.rentpropertiesapi.dto.request.RentalPropertyRequestDto;
import fr.properties.rentpropertiesapi.dto.request.patch.RentalPropertyRequestDtoPatch;
import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;

import java.util.List;

public class RentalPropertyDtoSample {

    public static RentalPropertyRequestDtoPatch oneRentalPropertyPatchRequest() {
        return RentalPropertyRequestDtoPatch.builder()
                .rentAmount(1000.00)
                .build();
    }

    public static RentalPropertyRequestDto oneRentalPropertyRequest() {
        return RentalPropertyRequestDto.builder()
                .description("Appartement spacieux avec vue sur l'ESGI")
                .town("Paris")
                .address("77 Rue des roses")
                .propertyType("Appartement")
                .rentAmount(750.90)
                .securityDepositAmount(1200.90)
                .area(37.48)
                .numberOfBedrooms(2)
                .floorNumber(1)
                .numberOfFloors(3)
                .constructionYear("1990")
                .energyClassification("B")
                .hasElevator(false)
                .hasIntercom(false)
                .hasBalcony(true)
                .hasParkingSpace(false)
                .build();
    }

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
