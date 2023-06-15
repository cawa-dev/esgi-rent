package fr.properties.rentpropertiesapi.mapper;

import java.util.List;

import fr.properties.rentpropertiesapi.domain.EnergyClassificationEntity;
import fr.properties.rentpropertiesapi.domain.PropertyTypeEntity;
import fr.properties.rentpropertiesapi.domain.RentalPropertyEntity;
import fr.properties.rentpropertiesapi.dto.request.RentalPropertyRequestDto;
import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RentalPropertyDtoMapper {

    public RentalPropertyResponseDto mapToDto(RentalPropertyEntity rentalProperty) {
        return RentalPropertyResponseDto.builder()
                .address(rentalProperty.getAddress())
                .area(rentalProperty.getArea())
                .description(rentalProperty.getDescription())
                .propertyType(rentalProperty.getPropertyType().getDesignation())
                .rentAmount(rentalProperty.getRentAmount())
                .securityDepositAmount(rentalProperty.getSecurityDepositAmount())
                .town(rentalProperty.getTown())
                .build();
    }

    public List<RentalPropertyResponseDto> mapToDtoList(List<RentalPropertyEntity> rentalProperties) {
        return rentalProperties.stream()
                .map(this::mapToDto)
                .toList();
    }

    public RentalPropertyEntity mapToEntity(RentalPropertyRequestDto rentalPropertyRequestDto) {
        return new RentalPropertyEntity(
                rentalPropertyRequestDto.description(),
                rentalPropertyRequestDto.town(),
                rentalPropertyRequestDto.address(),
                new PropertyTypeEntity(rentalPropertyRequestDto.propertyType()),
                rentalPropertyRequestDto.rentAmount(),
                rentalPropertyRequestDto.securityDepositAmount(),
                rentalPropertyRequestDto.area(),
                rentalPropertyRequestDto.numberOfBedrooms(),
                rentalPropertyRequestDto.floorNumber(),
                rentalPropertyRequestDto.numberOfFloors(),
                rentalPropertyRequestDto.constructionYear(),
                new EnergyClassificationEntity(rentalPropertyRequestDto.energyClassification()),
                rentalPropertyRequestDto.hasElevator(),
                rentalPropertyRequestDto.hasIntercom(),
                rentalPropertyRequestDto.hasBalcony(),
                rentalPropertyRequestDto.hasParkingSpace()
        );
    }
}
