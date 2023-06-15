package fr.properties.rentpropertiesapi.service;

import fr.properties.rentpropertiesapi.domain.PropertyTypeEntity;
import fr.properties.rentpropertiesapi.domain.RentalPropertyEntity;
import fr.properties.rentpropertiesapi.dto.request.RentalPropertyRequestDto;
import fr.properties.rentpropertiesapi.dto.request.patch.RentalPropertyRequestDtoPatch;
import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;
import fr.properties.rentpropertiesapi.exception.NotFoundRentalPropertyException;
import fr.properties.rentpropertiesapi.mapper.RentalPropertyMapper;
import fr.properties.rentpropertiesapi.repository.RentalPropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalPropertyService {

    private final RentalPropertyRepository rentalPropertyRepository;
    private final RentalPropertyMapper rentalPropertyMapper;


    public List<RentalPropertyResponseDto> getRentalProperties() {
        List<RentalPropertyEntity> rentalProperties = rentalPropertyRepository.findAll();

        return rentalPropertyMapper.mapToDtoList(rentalProperties);
    }


    public RentalPropertyResponseDto getRentalProperty(int id) {
        return rentalPropertyRepository.findById(id)
                .map(rentalPropertyMapper::mapToDto)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien immobilier avec l'id : " + id + " est introuvable"));
    }


    public void createRentalProperty(RentalPropertyRequestDto rentalPropertyRequestDto) {
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyMapper.mapToEntity(rentalPropertyRequestDto);

        rentalPropertyRepository.save(rentalPropertyEntity);
    }

    public void updateRentalProperty(int id, RentalPropertyRequestDto rentalPropertyRequestDto) {
        Optional<RentalPropertyEntity> optionalExistingRentalProperty = rentalPropertyRepository.findById(id);

        if (optionalExistingRentalProperty.isPresent()) {
            RentalPropertyEntity existingRentalProperty = optionalExistingRentalProperty.get();
            existingRentalProperty.setDescription(rentalPropertyRequestDto.description());
            existingRentalProperty.setTown(rentalPropertyRequestDto.town());
            existingRentalProperty.setAddress(rentalPropertyRequestDto.address());
            existingRentalProperty.setPropertyType(new PropertyTypeEntity(rentalPropertyRequestDto.propertyType()));
            existingRentalProperty.setRentAmount(rentalPropertyRequestDto.rentAmount());
            existingRentalProperty.setSecurityDepositAmount(rentalPropertyRequestDto.securityDepositAmount());
            existingRentalProperty.setArea(rentalPropertyRequestDto.area());

            rentalPropertyRepository.save(existingRentalProperty);
        } else {
            RentalPropertyEntity newRentalProperty = rentalPropertyMapper.mapToEntity(rentalPropertyRequestDto);

            rentalPropertyRepository.save(newRentalProperty);
        }
    }

    public void patchRentalProperty(int id, RentalPropertyRequestDtoPatch propertyRequestDtoPatch) {
        RentalPropertyEntity rentalProperty = rentalPropertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien immobilier avec l'id : " + id + " est introuvable"));

        rentalProperty.setRentAmount(propertyRequestDtoPatch.rentAmount());

        rentalPropertyRepository.save(rentalProperty);
    }

    public void deleteRentalProperty(int id) {
        rentalPropertyRepository.deleteById(id);
    }
}
