package fr.rent.front.samples;

import fr.rent.front.dto.request.RentalCarRequestDto;
import fr.rent.front.dto.request.patch.RentalCarRequestDtoPatch;
import fr.rent.front.dto.response.RentalCarResponseDto;

import java.util.List;

public class RentalCarResponseDtoSample {
    public static List<RentalCarResponseDto> manyRentalCarsResponseDto() {
        return List.of(
                new RentalCarResponseDto(
                        "Tesla",
                        "Model S",
                        100.0,
                        5000.0,
                        5,
                        4,
                        true
                ),
                new RentalCarResponseDto(
                        "Tesla",
                        "Model S",
                        100.0,
                        5000.0,
                        5,
                        4,
                        true
                )
        );

    }

    public static RentalCarResponseDto oneRentalCarResponseDto() {
        return new RentalCarResponseDto(
                "Tesla",
                "Model S",
                100.0,
                5000.0,
                5,
                4,
                true
        );
    }

    public static String oneValidRentalCarStringResponseDto() {
        return "{\"brand\":\"Tesla\",\"model\":\"Model S\",\"rentAmount\":100.0,\"securityDepositAmount\":5000.0,\"numberOfSeats\":5,\"numberOfDoors\":4,\"hasAirConditioning\":true}";
    }

    public static String manyValidRentalCarsStringResponseDto() {
        return "[{\"brand\":\"Tesla\",\"model\":\"Model S\",\"rentAmount\":100.0,\"securityDepositAmount\":5000.0,\"numberOfSeats\":5,\"numberOfDoors\":4,\"hasAirConditioning\":true},{\"brand\":\"Tesla\",\"model\":\"Model S\",\"rentAmount\":100.0,\"securityDepositAmount\":5000.0,\"numberOfSeats\":5,\"numberOfDoors\":4,\"hasAirConditioning\":true}]";
    }

    public static RentalCarRequestDtoPatch oneRentalCarRequestDtoPatch() {
        return new RentalCarRequestDtoPatch(1200.0);
    }

    public static String oneRentalCarStringRequestDtoPatch() {
        return "{" +
                "\"rentAmount\":1200.0" +
                "}";    }

    public static RentalCarRequestDto oneRentalCarRequestDto() {
        return new RentalCarRequestDto(
                "Mercedes-Benz",
                "S-Class",
                120.0,
                7000.0,
                5,
                4,
                true
        );
    }

    public static String oneRentalCarStringRequestDto() {
        return "{\"brand\":\"Mercedes-Benz\",\"model\":\"S-Class\",\"rentAmount\":120.0,\"securityDepositAmount\":7000.0,\"numberOfSeats\":5,\"numberOfDoors\":4,\"hasAirConditioning\":true}";
    }
}
