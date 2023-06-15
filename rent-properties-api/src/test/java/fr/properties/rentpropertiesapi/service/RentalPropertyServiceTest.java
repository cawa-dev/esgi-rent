package fr.properties.rentpropertiesapi.service;

import java.util.List;
import java.util.Optional;

import fr.properties.rentpropertiesapi.domain.RentalPropertyEntity;
import fr.properties.rentpropertiesapi.dto.request.RentalPropertyRequestDto;
import fr.properties.rentpropertiesapi.dto.request.patch.RentalPropertyRequestDtoPatch;
import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;
import fr.properties.rentpropertiesapi.exception.NotFoundRentalPropertyException;
import fr.properties.rentpropertiesapi.mapper.RentalPropertyDtoMapper;
import fr.properties.rentpropertiesapi.repository.RentalPropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.properties.rentpropertiesapi.samples.RentalPropertyDtoSample.*;
import static fr.properties.rentpropertiesapi.samples.RentalPropertyEntitySample.oneRentalPropertyEntity;
import static fr.properties.rentpropertiesapi.samples.RentalPropertyEntitySample.rentalPropertyEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
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

    @Test
    void shouldUpdateExistingRentalProperty() {
        // GIVEN
        int id = 1;
        RentalPropertyEntity existingRentalProperty = oneRentalPropertyEntity();
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();

        // WHEN
        when(rentalPropertyRepository.findById(id)).thenReturn(Optional.of(existingRentalProperty));
        rentalPropertyService.updateRentalProperty(id, rentalPropertyRequestDto);

        // THEN
        assertThat(existingRentalProperty.getDescription()).isEqualTo(rentalPropertyRequestDto.description());
        assertThat(existingRentalProperty.getTown()).isEqualTo(rentalPropertyRequestDto.town());
        assertThat(existingRentalProperty.getAddress()).isEqualTo(rentalPropertyRequestDto.address());
        assertThat(existingRentalProperty.getPropertyType())
                .hasFieldOrPropertyWithValue("designation", rentalPropertyRequestDto.propertyType());
        assertThat(existingRentalProperty.getRentAmount()).isEqualTo(rentalPropertyRequestDto.rentAmount());
        assertThat(existingRentalProperty.getSecurityDepositAmount()).isEqualTo(rentalPropertyRequestDto.securityDepositAmount());
        assertThat(existingRentalProperty.getArea()).isEqualTo(rentalPropertyRequestDto.area());

        verify(rentalPropertyRepository, times(1)).findById(id);
        verify(rentalPropertyRepository, times(1)).save(existingRentalProperty);
        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    void shouldCreateRentalPropertyWhenTryingToUpdateANonExistingRentalProperty() {
        // GIVEN
        int id = 2;
        var rentalPropertyRequestDto = oneRentalPropertyRequest();
        var newRentalProperty = oneRentalPropertyEntity();

        // WHEN
        when(rentalPropertyRepository.findById(id)).thenReturn(Optional.empty());
        when(rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto)).thenReturn(newRentalProperty);

        rentalPropertyService.updateRentalProperty(id, rentalPropertyRequestDto);

        // THEN
        verify(rentalPropertyRepository, times(1)).findById(id);
        verify(rentalPropertyRepository, times(1)).save(any(RentalPropertyEntity.class));
        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    void shouldPatchRentAmountOnExistingRentalProperty() {
        // GIVEN
        int id = 1;
        RentalPropertyRequestDtoPatch propertyRequestDtoPatch = oneRentalPropertyPatchRequest();
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();

        given(rentalPropertyRepository.findById(id)).willReturn(Optional.of(rentalPropertyEntity));
        ArgumentCaptor<RentalPropertyEntity> captor = ArgumentCaptor.forClass(RentalPropertyEntity.class);

        // WHEN
        rentalPropertyService.patchRentalProperty(id, propertyRequestDtoPatch);

        // THEN
        verify(rentalPropertyRepository, times(1)).findById(id);
        verify(rentalPropertyRepository, times(1)).save(captor.capture());

        var savedRentalProperty = captor.getValue();
        assertThat(savedRentalProperty).isNotNull();
        assertThat(savedRentalProperty.getRentAmount()).isEqualTo(1000.0);

        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    void shouldThrowNotFoundRentalPropertyExceptionWhenRentalPropertyIsNotFoundAndIsTryingToBePatch() {
        // GIVEN
        int id = 9999;
        var throwable = new NotFoundRentalPropertyException("Le bien immobilier avec l'id : " + id + " est introuvable");
        RentalPropertyRequestDtoPatch propertyRequestDtoPatch = oneRentalPropertyPatchRequest();

        // WHEN
        when(rentalPropertyRepository.findById(id)).thenThrow(throwable);

        // THEN
        assertThatExceptionOfType(NotFoundRentalPropertyException.class)
                .isThrownBy(() -> rentalPropertyService.patchRentalProperty(id, propertyRequestDtoPatch))
                .withMessage(throwable.getMessage());
        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    void shouldDeleteRentalProperty() {
        // GIVEN
        int id = 0;

        // WHEN
        rentalPropertyService.deleteRentalProperty(id);

        //THEN
        verify(rentalPropertyRepository).deleteById(id);
        verifyNoMoreInteractions(rentalPropertyRepository);

    }
}
