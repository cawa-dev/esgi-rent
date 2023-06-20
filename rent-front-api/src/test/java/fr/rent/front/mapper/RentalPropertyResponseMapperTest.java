package fr.rent.front.mapper;

import fr.rent.front.dto.request.RentalPropertyRequestDto;
import fr.rent.front.dto.request.patch.RentalPropertyRequestDtoPatch;
import fr.rent.front.dto.response.RentalPropertyResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static fr.rent.front.samples.RentalPropertyResponseDtoSample.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RentalPropertyMapperTest {

    RentalPropertyMapper rentalPropertyMapper;

    @BeforeEach
    void setUp() {
        rentalPropertyMapper = new RentalPropertyMapper();
    }

    @Test
    void shouldMapToListResponse() throws IOException {
        // GIVEN
        List<RentalPropertyResponseDto> expectedMAnyRentalPropertyResponseDto = manyRentalPropertiesResponseDto();
        String manyRentalPropertyStringResponse = manyValidRentalPropertiesStringResponseDto();

        // WHEN
        List<RentalPropertyResponseDto> rentalPropertyResponseDto = rentalPropertyMapper.mapToListResponse(manyRentalPropertyStringResponse);

        // THEN
        assertThat(rentalPropertyResponseDto).containsSequence(expectedMAnyRentalPropertyResponseDto);
    }

    @Test
    void shouldMapToResponse() throws IOException {
        // GIVEN
        RentalPropertyResponseDto expectedRentalPropertyResponseDto = oneRentalPropertyResponseDto();
        String rentalPropertyStringResponse = oneValidRentalPropertyStringResponseDto();

        // WHEN
        RentalPropertyResponseDto rentalPropertyResponseDto = rentalPropertyMapper.mapToResponse(rentalPropertyStringResponse);

        // THEN
        assertThat(rentalPropertyResponseDto).isEqualTo(expectedRentalPropertyResponseDto);
    }

    @Test
    void shouldMapToBodyRequest(){
        // GIVEN
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequestDto();
        String expectedRentalPropertyStringRequest = oneValidRentalPropertyStringRequestDto();

        // WHEN
        String actualRentalPropertyRequestDto = rentalPropertyMapper.mapToBodyRequest(rentalPropertyRequestDto);

        // THEN
        assertThat(actualRentalPropertyRequestDto).isEqualTo(expectedRentalPropertyStringRequest);
    }

    @Test
    void shouldMapToBodyPatch(){
        // GIVEN
        RentalPropertyRequestDtoPatch rentalPropertyRequestDto = oneRentalPropertyRequestDtoPatch();
        String expectedRentalPropertyStringRequestDtoPatch = oneRentalPropertyStringRequestDtoPatch();

        // WHEN
        String actualRentalPropertyRequestDto = rentalPropertyMapper.mapToBodyRequestPatch(rentalPropertyRequestDto);

        // THEN
        assertThat(actualRentalPropertyRequestDto).isEqualTo(expectedRentalPropertyStringRequestDtoPatch);
    }
}