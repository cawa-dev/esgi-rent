package fr.cars.rentcarsapi.samples;

import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;

import java.util.List;

public class RentalCarDtoSample {

    public static RentalCarResponseDto oneRentalCarResponse() {
        return RentalCarResponseDto.builder()
                .brand("BMW")
                .model("Serie 1")
                .rentAmount(790.9)
                .securityDepositAmount(1550.9)
                .numberOfSeats(5)
                .numberOfDoors(4)
                .hasAirConditioning(true)
                .build();
    }

    public static List<RentalCarResponseDto> rentalCarsResponseList() {
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();

        return List.of(rentalCarResponseDto);
    }
}
