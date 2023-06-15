package fr.cars.rentcarsapi.service;

import fr.cars.rentcarsapi.domain.RentalCarEntity;
import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;
import fr.cars.rentcarsapi.mapper.RentalCarMapper;
import fr.cars.rentcarsapi.repository.RentalCarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static fr.cars.rentcarsapi.samples.RentalCarDtoSample.rentalCarsResponseList;
import static fr.cars.rentcarsapi.samples.RentalCarEntitySample.rentalCarEntities;
import static org.assertj.core.api.Assertions.assertThat;
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
}
