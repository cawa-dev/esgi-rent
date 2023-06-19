package fr.rent.front.mapper;

import fr.rent.front.dto.RentalPropertyResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static fr.rent.front.samples.RentalPropertyResponseDtoSample.*;

class RentalPropertyResponseMapperTest {
    RentalPropertyResponseMapper rentalPropertyMapper;

    @BeforeEach
    void setUp() {
        rentalPropertyMapper = new RentalPropertyResponseMapper();
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
    void shouldNotListMapToResponse() throws IOException {
        // GIVEN
        String InvalidListRentalPropertyStringResponse = manyInvalidRentalPropertiesStringResponseDto();

        // WHEN & THEN
        assertThrows(IOException.class, () -> rentalPropertyMapper.mapToResponse(InvalidListRentalPropertyStringResponse));
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
    void shouldNotMapToResponse() throws IOException {
        // GIVEN
        String InvalidRentalPropertyStringResponse = oneInvalidRentalPropertyStringResponseDto();

        // WHEN & THEN
        assertThrows(IOException.class, () -> rentalPropertyMapper.mapToResponse(InvalidRentalPropertyStringResponse));
    }
}