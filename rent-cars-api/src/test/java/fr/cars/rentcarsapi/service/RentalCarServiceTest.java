package fr.cars.rentcarsapi.service;

import fr.cars.rentcarsapi.domain.RentalCarEntity;
import fr.cars.rentcarsapi.dto.request.RentalCarRequestDto;
import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;
import fr.cars.rentcarsapi.exception.NotFoundRentalCarException;
import fr.cars.rentcarsapi.mapper.RentalCarMapper;
import fr.cars.rentcarsapi.repository.RentalCarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static fr.cars.rentcarsapi.samples.RentalCarDtoSample.*;
import static fr.cars.rentcarsapi.samples.RentalCarEntitySample.oneRentalCarEntity;
import static fr.cars.rentcarsapi.samples.RentalCarEntitySample.rentalCarEntities;
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
    RentalCarRepository rentalCarRepository;

    @Test
    void shouldGetRentalCars() {
        // GIVEN
        List<RentalCarEntity> rentalCarEntityList = rentalCarEntities();
        List<RentalCarResponseDto> rentalCarResponseList = rentalCarsResponseList();

        // WHEN
        when(rentalCarRepository.findAll()).thenReturn(rentalCarEntityList);
        when(rentalCarMapper.mapToDtoList(rentalCarEntityList)).thenReturn(rentalCarResponseList);

        List<RentalCarResponseDto> rentalCars = rentalCarService.getRentalCars();

        // THEN
        assertThat(rentalCars)
                .usingRecursiveComparison()
                .isEqualTo(rentalCarResponseList);
        verify(rentalCarRepository).findAll();
        verify(rentalCarMapper).mapToDtoList(rentalCarEntityList);
        verifyNoMoreInteractions(rentalCarRepository, rentalCarMapper);
    }

    @Test
    void shouldGetRentalCar() {
        // GIVEN
        int id = 1;
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();
        RentalCarResponseDto rentalCarResponse = oneRentalCarResponse();

        // WHEN
        when(rentalCarRepository.findById(id)).thenReturn(Optional.of(rentalCarEntity));
        when(rentalCarMapper.mapToDto(rentalCarEntity)).thenReturn(rentalCarResponse);

        RentalCarResponseDto rentalCar = rentalCarService.getRentalCar(id);

        // THEN
        assertThat(rentalCar)
                .usingRecursiveComparison()
                .isEqualTo(rentalCarResponse);
        verify(rentalCarRepository).findById(id);
        verify(rentalCarMapper).mapToDto(rentalCarEntity);
        verifyNoMoreInteractions(rentalCarRepository, rentalCarRepository);
    }

    @Test
    void shouldThrowNotFoundRentalCarExceptionWhenRentalCarIsNotFound() {
        // GIVEN
        int id = 9999;
        var throwable = new NotFoundRentalCarException("Le véhicule avec l'id : " + id + " est introuvable");

        // WHEN
        when(rentalCarRepository.findById(id)).thenThrow(throwable);

        // THEN
        assertThatExceptionOfType(NotFoundRentalCarException.class)
                .isThrownBy(() -> rentalCarService.getRentalCar(id))
                .withMessage(throwable.getMessage());
        verifyNoInteractions(rentalCarMapper);
    }

    @Test
    void shouldCreateRentalCar() {
        // GIVEN
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        // WHEN
        when(rentalCarMapper.mapToEntity(rentalCarRequestDto)).thenReturn(rentalCarEntity);
        when(rentalCarRepository.save(rentalCarEntity)).thenReturn(rentalCarEntity);

        rentalCarService.createRentalCar(rentalCarRequestDto);

        // THEN
        verify(rentalCarMapper).mapToEntity(rentalCarRequestDto);
        verify(rentalCarRepository).save(rentalCarEntity);
        verifyNoMoreInteractions(rentalCarMapper, rentalCarRepository);
    }

    @Test
    void shouldUpdateExistingRentalCar() {
        // GIVEN
        int id = 1;
        RentalCarEntity existingRentalCar = oneRentalCarEntity();
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();

        // WHEN
        when(rentalCarRepository.findById(id)).thenReturn(Optional.of(existingRentalCar));
        rentalCarService.updateRentalCar(id, rentalCarRequestDto);

        // THEN
        assertThat(existingRentalCar.getBrand()).isEqualTo(rentalCarRequestDto.brand());
        assertThat(existingRentalCar.getModel()).isEqualTo(rentalCarRequestDto.model());
        assertThat(existingRentalCar.getRentAmount()).isEqualTo(rentalCarRequestDto.rentAmount());
        assertThat(existingRentalCar.getSecurityDepositAmount()).isEqualTo(rentalCarRequestDto.securityDepositAmount());
        assertThat(existingRentalCar.getNumberOfSeats()).isEqualTo(rentalCarRequestDto.numberOfSeats());
        assertThat(existingRentalCar.getNumberOfDoors()).isEqualTo(rentalCarRequestDto.numberOfDoors());
        assertThat(existingRentalCar.getHasAirConditioning()).isEqualTo(rentalCarRequestDto.hasAirConditioning());

        verify(rentalCarRepository, times(1)).findById(id);
        verify(rentalCarRepository, times(1)).save(existingRentalCar);
        verifyNoMoreInteractions(rentalCarRepository);
    }

    @Test
    void shouldCreateRentalCarWhenTryingToUpdateANonExistingRentalCar() {
        // GIVEN
        int id = 2;
        var rentalCarRequestDto = oneRentalCarRequest();
        var newRentalCar = oneRentalCarEntity();

        // WHEN
        when(rentalCarRepository.findById(id)).thenReturn(Optional.empty());
        when(rentalCarMapper.mapToEntity(rentalCarRequestDto)).thenReturn(newRentalCar);

        rentalCarService.updateRentalCar(id, rentalCarRequestDto);

        // THEN
        verify(rentalCarRepository, times(1)).findById(id);
        verify(rentalCarRepository, times(1)).save(any(RentalCarEntity.class));
        verifyNoMoreInteractions(rentalCarRepository);
    }
}
