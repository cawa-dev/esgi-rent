package fr.rent.front.service;

import fr.rent.front.api.RentalPropertyApiClient;
import fr.rent.front.dto.request.RentalPropertyRequestDto;
import fr.rent.front.dto.request.patch.RentalPropertyRequestDtoPatch;
import fr.rent.front.dto.response.RentalPropertyResponseDto;
import fr.rent.front.exception.NotFoundRentalPropertyException;
import fr.rent.front.mapper.RentalPropertyMapper;
import fr.rent.front.samples.RentalPropertyResponseDtoSample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static fr.rent.front.samples.RentalPropertyResponseDtoSample.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalPropertyServiceTest {

    @InjectMocks
    RentalPropertyService rentalPropertyService;

    @Mock
    RentalPropertyMapper rentalPropertyMapper;

    @Mock
    RentalPropertyApiClient rentalPropertyApiClient;

    @Test
    void shouldGetRentalProperties() throws IOException, InterruptedException {
        // GIVEN
        String rentalPropertyApiResponse = manyValidRentalPropertiesStringResponseDto();
        List<RentalPropertyResponseDto> rentalPropertyResponseList = manyRentalPropertiesResponseDto();

        // WHEN
        when(rentalPropertyApiClient.fetchRentalProperties()).thenReturn(rentalPropertyApiResponse);
        when(rentalPropertyMapper.mapToListResponse(rentalPropertyApiResponse)).thenReturn(rentalPropertyResponseList);

        List<RentalPropertyResponseDto> rentalProperties = rentalPropertyService.getRentalProperties();

        // THEN
        assertThat(rentalProperties)
                .usingRecursiveComparison()
                .isEqualTo(rentalPropertyResponseList);
        verify(rentalPropertyApiClient).fetchRentalProperties();
        verify(rentalPropertyMapper).mapToListResponse(rentalPropertyApiResponse);
        verifyNoMoreInteractions(rentalPropertyApiClient, rentalPropertyMapper);
    }

    @Test
    void shouldGetRentalProperty() throws IOException, InterruptedException {
        // GIVEN
        String id = "1";
        String rentalPropertyApiResponse = oneValidRentalPropertyStringResponseDto();
        RentalPropertyResponseDto rentalPropertyResponse = oneRentalPropertyResponseDto();

        // WHEN
        when(rentalPropertyApiClient.fetchRentalProperty(id)).thenReturn(rentalPropertyApiResponse);
        when(rentalPropertyMapper.mapToResponse(rentalPropertyApiResponse)).thenReturn(rentalPropertyResponse);

        RentalPropertyResponseDto rentalProperty = rentalPropertyService.getRentalProperty(id);

        // THEN
        assertThat(rentalProperty)
                .usingRecursiveComparison()
                .isEqualTo(rentalPropertyResponse);
        verify(rentalPropertyApiClient).fetchRentalProperty(id);
        verify(rentalPropertyMapper).mapToResponse(rentalPropertyApiResponse);
        verifyNoMoreInteractions(rentalPropertyApiClient, rentalPropertyMapper);
    }

    @Test
    void shouldThrowNotFoundRentalPropertyExceptionWhenRentalPropertyIsNotFound() throws IOException, InterruptedException {
        // GIVEN
        String id = "9999";
        String errorMessage = "Le bien immobilier avec l'id : " + id + " est introuvable";

        // WHEN
        when(rentalPropertyApiClient.fetchRentalProperty(id)).thenThrow(new NotFoundRentalPropertyException(errorMessage));

        // THEN
        assertThatExceptionOfType(NotFoundRentalPropertyException.class)
                .isThrownBy(() -> rentalPropertyService.getRentalProperty(id))
                .withMessage(errorMessage);
        verifyNoInteractions(rentalPropertyMapper);
    }

    @Test
    void shouldCreateRentalProperty() {
        // GIVEN
        RentalPropertyRequestDto rentalPropertyRequestDto = RentalPropertyResponseDtoSample.oneRentalPropertyRequestDto();

        // WHEN
        rentalPropertyService.createRentalProperty(rentalPropertyRequestDto);

        // THEN
        verify(rentalPropertyApiClient).postRentalProperty(rentalPropertyRequestDto);
        verifyNoMoreInteractions(rentalPropertyApiClient, rentalPropertyMapper);
    }

    @Test
    void shouldUpdateRentalProperty() {
        // GIVEN
        String id = "77";
        RentalPropertyRequestDto rentalPropertyRequestDto = RentalPropertyResponseDtoSample.oneRentalPropertyRequestDto();

        // WHEN
        rentalPropertyService.updateRentalProperty(id, rentalPropertyRequestDto);

        // THEN
        verify(rentalPropertyApiClient).putRentalProperty(id, rentalPropertyRequestDto);
        verifyNoMoreInteractions(rentalPropertyApiClient, rentalPropertyMapper);
    }

    @Test
    void shouldPatchRentalProperty() {
        // GIVEN
        String id = "77";
        RentalPropertyRequestDto rentalPropertyRequestDto = RentalPropertyResponseDtoSample.oneRentalPropertyRequestDto();
        RentalPropertyRequestDtoPatch rentalPropertyRequestDtoPatch = new RentalPropertyRequestDtoPatch(rentalPropertyRequestDto.rentAmount());

        // WHEN
        rentalPropertyService.patchRentalProperty(id, rentalPropertyRequestDtoPatch);

        // THEN
        verify(rentalPropertyApiClient).patchRentalProperty(id, rentalPropertyRequestDtoPatch);
        verifyNoMoreInteractions(rentalPropertyApiClient, rentalPropertyMapper);
    }
}