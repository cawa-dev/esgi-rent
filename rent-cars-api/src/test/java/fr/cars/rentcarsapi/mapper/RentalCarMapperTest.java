package fr.cars.rentcarsapi.mapper;

import fr.cars.rentcarsapi.domain.RentalCarEntity;
import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.cars.rentcarsapi.samples.RentalCarDtoSample.oneRentalCarResponse;
import static fr.cars.rentcarsapi.samples.RentalCarEntitySample.oneRentalCarEntity;
import static fr.cars.rentcarsapi.samples.RentalCarEntitySample.rentalCarEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class RentalCarMapperTest {

    RentalCarMapper rentalCarMapperMapper;

    @BeforeEach
    void setUp() {
        rentalCarMapperMapper = new RentalCarMapper();
    }

    @Test
    void shouldMapToDto() {
        // GIVEN
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        RentalCarResponseDto expectedRentalCarResponseDto = oneRentalCarResponse();

        // WHEN
        RentalCarResponseDto rentalCarResponseDto = rentalCarMapperMapper.mapToDto(rentalCarEntity);

        // THEN
        assertThat(expectedRentalCarResponseDto).isEqualTo(rentalCarResponseDto);
    }

    @Test
    void shouldMapToDtoList() {
        // GIVEN
        List<RentalCarEntity> rentalCarsEntities = rentalCarEntities();

        // WHEN
        List<RentalCarResponseDto> rentalCarResponseList = rentalCarMapperMapper.mapToDtoList(rentalCarsEntities);

        // THEN
        assertThat(rentalCarResponseList).isNotNull()
                .hasSize(1)
                .extracting("brand",
                        "model",
                        "rentAmount",
                        "securityDepositAmount",
                        "numberOfSeats",
                        "numberOfDoors",
                        "hasAirConditioning"
                )
                .containsExactlyInAnyOrder(
                        tuple("BMW",
                                "Serie 1",
                                790.9,
                                1550.9,
                                5,
                                4,
                                true
                        )
                );
    }

}
