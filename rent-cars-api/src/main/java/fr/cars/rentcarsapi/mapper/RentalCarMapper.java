package fr.cars.rentcarsapi.mapper;

import fr.cars.rentcarsapi.domain.RentalCarEntity;
import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalCarMapper {

    public RentalCarResponseDto mapToDto(RentalCarEntity rentalCarEntity) {
        return RentalCarResponseDto.builder()
                .brand(rentalCarEntity.getBrand())
                .model(rentalCarEntity.getModel())
                .rentAmount(rentalCarEntity.getRentAmount())
                .securityDepositAmount(rentalCarEntity.getSecurityDepositAmount())
                .numberOfSeats(rentalCarEntity.getNumberOfSeats())
                .numberOfDoors(rentalCarEntity.getNumberOfDoors())
                .hasAirConditioning(rentalCarEntity.getHasAirConditioning())
                .build();
    }

    public List<RentalCarResponseDto> mapToDtoList(List<RentalCarEntity> rentalCarEntities) {
        return rentalCarEntities.stream()
                .map(this::mapToDto)
                .toList();
    }
}
