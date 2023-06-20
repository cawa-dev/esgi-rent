package fr.rent.front.service;

import fr.rent.front.api.RentalCarApiClient;
import fr.rent.front.dto.request.RentalCarRequestDto;
import fr.rent.front.dto.request.patch.RentalCarRequestDtoPatch;
import fr.rent.front.dto.response.RentalCarResponseDto;
import fr.rent.front.exception.NotFoundRentalCarException;
import fr.rent.front.mapper.RentalCarMapper;
import fr.rent.front.samples.RentalCarResponseDtoSample;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static fr.rent.front.samples.RentalCarResponseDtoSample.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalCarServiceTest {

    @InjectMocks
    RentalCarService rentalCarService;

    @Mock
    RentalCarMapper rentalCarMapper;

    @Mock
    RentalCarApiClient rentalCarApiClient;

    @Test
    void shouldGetRentalCars() throws IOException, InterruptedException {
        // GIVEN
        String rentalCarApiResponse = manyValidRentalCarsStringResponseDto();
        List<RentalCarResponseDto> rentalCarResponseList = manyRentalCarsResponseDto();

        // WHEN
        when(rentalCarApiClient.fetchRentalCars()).thenReturn(rentalCarApiResponse);
        when(rentalCarMapper.mapToListResponse(rentalCarApiResponse)).thenReturn(rentalCarResponseList);

        List<RentalCarResponseDto> rentalCars = rentalCarService.getRentalCars();

        // THEN
        assertThat(rentalCars)
                .usingRecursiveComparison()
                .isEqualTo(rentalCarResponseList);
        verify(rentalCarApiClient).fetchRentalCars();
        verify(rentalCarMapper).mapToListResponse(rentalCarApiResponse);
        verifyNoMoreInteractions(rentalCarApiClient, rentalCarMapper);
    }

    @Test
    void shouldGetRentalCar() throws IOException, InterruptedException {
        // GIVEN
        String id = "1";
        String rentalCarApiResponse = oneValidRentalCarStringResponseDto();
        RentalCarResponseDto rentalCarResponse = oneRentalCarResponseDto();

        // WHEN
        when(rentalCarApiClient.fetchRentalCar(id)).thenReturn(rentalCarApiResponse);
        when(rentalCarMapper.mapToResponse(rentalCarApiResponse)).thenReturn(rentalCarResponse);

        RentalCarResponseDto rentalCar = rentalCarService.getRentalCar(id);

        // THEN
        assertThat(rentalCar)
                .usingRecursiveComparison()
                .isEqualTo(rentalCarResponse);
        verify(rentalCarApiClient).fetchRentalCar(id);
        verify(rentalCarMapper).mapToResponse(rentalCarApiResponse);
        verifyNoMoreInteractions(rentalCarApiClient, rentalCarMapper);
    }

    @Test
    void shouldThrowNotFoundRentalCarExceptionWhenRentalCarIsNotFound() throws IOException, InterruptedException {
        // GIVEN
        String id = "9999";
        String errorMessage = "Le bien immobilier avec l'id : " + id + " est introuvable";

        // WHEN
        when(rentalCarApiClient.fetchRentalCar(id)).thenThrow(new NotFoundRentalCarException(errorMessage));

        // THEN
        assertThatExceptionOfType(NotFoundRentalCarException.class)
                .isThrownBy(() -> rentalCarService.getRentalCar(id))
                .withMessage(errorMessage);
        verifyNoInteractions(rentalCarMapper);
    }

    @Test
    void shouldCreateRentalCar() {
        // GIVEN
        RentalCarRequestDto rentalCarRequestDto = RentalCarResponseDtoSample.oneRentalCarRequestDto();

        // WHEN
        rentalCarService.createRentalCar(rentalCarRequestDto);

        // THEN
        verify(rentalCarApiClient).postRentalCar(rentalCarRequestDto);
        verifyNoMoreInteractions(rentalCarApiClient, rentalCarMapper);
    }

    @Test
    void shouldUpdateRentalCar() {
        // GIVEN
        String id = "77";
        RentalCarRequestDto rentalCarRequestDto = RentalCarResponseDtoSample.oneRentalCarRequestDto();

        // WHEN
        rentalCarService.updateRentalCar(id, rentalCarRequestDto);

        // THEN
        verify(rentalCarApiClient).putRentalCar(id, rentalCarRequestDto);
        verifyNoMoreInteractions(rentalCarApiClient, rentalCarMapper);
    }

    @Test
    void shouldPatchRentalCar() {
        // GIVEN
        String id = "77";
        RentalCarRequestDto rentalCarRequestDto = RentalCarResponseDtoSample.oneRentalCarRequestDto();
        RentalCarRequestDtoPatch rentalCarRequestDtoPatch = new RentalCarRequestDtoPatch(rentalCarRequestDto.rentAmount());

        // WHEN
        rentalCarService.patchRentalCar(id, rentalCarRequestDtoPatch);

        // THEN
        verify(rentalCarApiClient).patchRentalCar(id, rentalCarRequestDtoPatch);
        verifyNoMoreInteractions(rentalCarApiClient, rentalCarMapper);
    }
}