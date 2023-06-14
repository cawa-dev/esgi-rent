package fr.properties.rentpropertiesapi.mapper;

import java.util.List;

import fr.properties.rentpropertiesapi.domain.RentalPropertyEntity;
import fr.properties.rentpropertiesapi.dto.request.RentalPropertyRequestDto;
import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.properties.rentpropertiesapi.samples.RentalPropertyDtoSample.oneRentalPropertyRequest;
import static fr.properties.rentpropertiesapi.samples.RentalPropertyDtoSample.oneRentalPropertyResponse;
import static fr.properties.rentpropertiesapi.samples.RentalPropertyEntitySample.oneRentalPropertyEntity;
import static fr.properties.rentpropertiesapi.samples.RentalPropertyEntitySample.rentalPropertyEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class RentalPropertyDtoMapperTest {

    private RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @BeforeEach
    void setUp() {
        rentalPropertyDtoMapper = new RentalPropertyDtoMapper();
    }

    @Test
    void shouldMapEntity() {
        // GIVEN
        RentalPropertyRequestDto rentalPropertyRequest = oneRentalPropertyRequest();
        RentalPropertyEntity expectedRentalPropertyEntity = oneRentalPropertyEntity();

        // WHEN
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequest);

        // THEN
        assertThat(rentalPropertyEntity).isEqualTo(expectedRentalPropertyEntity);
    }

    @Test
    void shouldMapToDto() {
        // GIVEN
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        RentalPropertyResponseDto expectedRentalPropertyResponseDto = oneRentalPropertyResponse();

        // WHEN
        RentalPropertyResponseDto rentalPropertyResponseDto = rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity);

        // THEN
        assertThat(rentalPropertyResponseDto).isEqualTo(expectedRentalPropertyResponseDto);
    }

    @Test
    void shouldMapToDtoList() {
        // GIVEN
        List<RentalPropertyEntity> rentalPropertyEntities = rentalPropertyEntities();

        // WHEN
        List<RentalPropertyResponseDto> rentalPropertyResponseList = rentalPropertyDtoMapper.mapToDtoList(rentalPropertyEntities);

        // THEN
        assertThat(rentalPropertyResponseList).isNotNull()
                .hasSize(1)
                .extracting("address",
                        "area",
                        "description",
                        "propertyType",
                        "rentAmount",
                        "securityDepositAmount",
                        "town"
                )
                .containsExactlyInAnyOrder(
                        tuple("77 Rue des roses",
                                37.48,
                                "Appartement spacieux avec vue sur l'ESGI",
                                "Appartement",
                                750.90,
                                1200.90,
                                "Paris"
                        )
                );
    }
}
