package fr.properties.rentpropertiesapi.mapper;

import java.util.List;

import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;
import fr.properties.rentpropertiesapi.entities.RentalPropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class RentalPropertyDtoMapper {

    public RentalPropertyResponseDto mapToDto(RentalPropertyEntity rentalProperty) {
        return new RentalPropertyResponseDto(
                rentalProperty.getAddress(),
                rentalProperty.getArea(),
                rentalProperty.getDescription(),
                rentalProperty.getPropertyType()
                        .getDesignation(),
                rentalProperty.getRentAmount(),
                rentalProperty.getSecurityDepositAmount(),
                rentalProperty.getTown()
        );
    }

    public List<RentalPropertyResponseDto> mapToDtoList(List<RentalPropertyEntity> rentalProperties) {
        return rentalProperties.stream()
                .map(this::mapToDto)
                .toList();
    }
}
