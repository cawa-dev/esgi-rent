package fr.cars.rentcarsapi.samples;

import fr.cars.rentcarsapi.dto.request.RentalCarRequestDto;
import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;

import java.util.List;

public class RentalCarDtoSample {

    public static RentalCarRequestDto oneRentalCarRequest() {
        return RentalCarRequestDto.builder()
                .brand("BMW")
                .model("Serie 1")
                .rentAmount(790.9)
                .securityDepositAmount(1550.9)
                .numberOfSeats(5)
                .numberOfDoors(4)
                .hasAirConditioning(true)
                .build();
    }

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
