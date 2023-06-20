package fr.rent.front.mapper;

import fr.rent.front.dto.request.RentalCarRequestDto;
import fr.rent.front.dto.request.patch.RentalCarRequestDtoPatch;
import fr.rent.front.dto.response.RentalCarResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static fr.rent.front.samples.RentalCarResponseDtoSample.*;
import static fr.rent.front.samples.RentalCarResponseDtoSample.manyRentalCarsResponseDto;
import static fr.rent.front.samples.RentalCarResponseDtoSample.manyValidRentalCarsStringResponseDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RentalCarMapperTest {

    RentalCarMapper rentalCarMapper;

    @BeforeEach
    void setUp() {
        rentalCarMapper = new RentalCarMapper();
    }

    @Test
    void shouldMapToListResponse() throws IOException {
        // GIVEN
        List<RentalCarResponseDto> expectedMAnyRentalCarResponseDto = manyRentalCarsResponseDto();
        String manyRentalCarStringResponse = manyValidRentalCarsStringResponseDto();

        // WHEN
        List<RentalCarResponseDto> rentalCarResponseDto = rentalCarMapper.mapToListResponse(manyRentalCarStringResponse);

        // THEN
        assertThat(rentalCarResponseDto).containsSequence(expectedMAnyRentalCarResponseDto);
    }

    @Test
    void shouldMapToResponse() throws IOException {
        // GIVEN
        RentalCarResponseDto expectedRentalCarResponseDto = oneRentalCarResponseDto();
        String rentalCarStringResponse = oneValidRentalCarStringResponseDto();

        // WHEN
        RentalCarResponseDto rentalCarResponseDto = rentalCarMapper.mapToResponse(rentalCarStringResponse);

        // THEN
        assertThat(rentalCarResponseDto).isEqualTo(expectedRentalCarResponseDto);
    }

    @Test
    void shouldMapToBodyRequest(){
        // GIVEN
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequestDto();
        String expectedRentalCarStringRequest = oneRentalCarStringRequestDto();

        // WHEN
        String actualRentalCarRequestDto = rentalCarMapper.mapToBodyRequest(rentalCarRequestDto);

        // THEN
        assertThat(actualRentalCarRequestDto).isEqualTo(expectedRentalCarStringRequest);
    }

    @Test
    void shouldMapToBodyPatch(){
        // GIVEN
        RentalCarRequestDtoPatch rentalCarRequestDto = oneRentalCarRequestDtoPatch();
        String expectedRentalCarStringRequestDtoPatch = oneRentalCarStringRequestDtoPatch();

        // WHEN
        String actualRentalCarRequestDto = rentalCarMapper.mapToBodyRequestPatch(rentalCarRequestDto);

        // THEN
        assertThat(actualRentalCarRequestDto).isEqualTo(expectedRentalCarStringRequestDtoPatch);
    }
}