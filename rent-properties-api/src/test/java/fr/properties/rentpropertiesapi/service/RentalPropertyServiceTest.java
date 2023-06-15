package fr.properties.rentpropertiesapi.service;

import java.util.List;
import java.util.Optional;

import fr.properties.rentpropertiesapi.domain.RentalPropertyEntity;
import fr.properties.rentpropertiesapi.dto.request.RentalPropertyRequestDto;
import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;
import fr.properties.rentpropertiesapi.exception.NotFoundRentalPropertyException;
import fr.properties.rentpropertiesapi.mapper.RentalPropertyDtoMapper;
import fr.properties.rentpropertiesapi.repository.RentalPropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.properties.rentpropertiesapi.samples.RentalPropertyDtoSample.*;
import static fr.properties.rentpropertiesapi.samples.RentalPropertyEntitySample.oneRentalPropertyEntity;
import static fr.properties.rentpropertiesapi.samples.RentalPropertyEntitySample.rentalPropertyEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalPropertyServiceTest {

    @InjectMocks
    RentalPropertyService rentalPropertyService;

    @Mock
    RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @Mock
    RentalPropertyRepository rentalPropertyRepository;

    @Test
    void shouldGetRentalProperties() {
        // GIVEN
        List<RentalPropertyEntity> rentalPropertyEntityList = rentalPropertyEntities();
        List<RentalPropertyResponseDto> rentalPropertyResponseList = rentalPropertyResponseList();

        // WHEN
        when(rentalPropertyRepository.findAll()).thenReturn(rentalPropertyEntityList);
        when(rentalPropertyDtoMapper.mapToDtoList(rentalPropertyEntityList)).thenReturn(rentalPropertyResponseList);

        List<RentalPropertyResponseDto> rentalProperties = rentalPropertyService.getRentalProperties();

        // THEN
        assertThat(rentalProperties)
                .usingRecursiveComparison()
                .isEqualTo(rentalPropertyResponseList);
        verify(rentalPropertyRepository).findAll();
        verify(rentalPropertyDtoMapper).mapToDtoList(rentalPropertyEntityList);
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

    @Test
    void shouldGetRentalProperty() {
        // GIVEN
        int id = 1;
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        RentalPropertyResponseDto rentalPropertyResponse = oneRentalPropertyResponse();

        // WHEN
        when(rentalPropertyRepository.findById(id)).thenReturn(Optional.of(rentalPropertyEntity));
        when(rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity)).thenReturn(rentalPropertyResponse);

        RentalPropertyResponseDto rentalProperty = rentalPropertyService.getRentalProperty(id);

        // THEN
        assertThat(rentalProperty)
                .usingRecursiveComparison()
                .isEqualTo(rentalPropertyResponse);
        verify(rentalPropertyRepository).findById(id);
        verify(rentalPropertyDtoMapper).mapToDto(rentalPropertyEntity);
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

    @Test
    void shouldThrowNotFoundRentalPropertyExceptionWhenRentalPropertyIsNotFound() {
        // GIVEN
        int id = 9999;
        var throwable = new NotFoundRentalPropertyException("Le bien immobilier avec l'id : " + id + " est introuvable");

        // WHEN
        when(rentalPropertyRepository.findById(id)).thenThrow(throwable);

        // THEN
        assertThatExceptionOfType(NotFoundRentalPropertyException.class)
                .isThrownBy(() -> rentalPropertyService.getRentalProperty(id))
                .withMessage(throwable.getMessage());
        verifyNoInteractions(rentalPropertyDtoMapper);
    }

    @Test
    void shouldCreateRentalProperty() {
        // GIVEN
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();

        // WHEN
        when(rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto)).thenReturn(rentalPropertyEntity);
        when(rentalPropertyRepository.save(rentalPropertyEntity)).thenReturn(rentalPropertyEntity);

        rentalPropertyService.createRentalProperty(rentalPropertyRequestDto);

        // THEN
        verify(rentalPropertyDtoMapper).mapToEntity(rentalPropertyRequestDto);
        verify(rentalPropertyRepository).save(rentalPropertyEntity);
        verifyNoMoreInteractions(rentalPropertyDtoMapper, rentalPropertyRepository);
    }
}
