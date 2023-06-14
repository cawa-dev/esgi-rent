package fr.properties.rentpropertiesapi.api;

import java.util.List;

import fr.properties.rentpropertiesapi.domain.RentalPropertyEntity;
import fr.properties.rentpropertiesapi.dto.request.RentalPropertyRequestDto;
import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;
import fr.properties.rentpropertiesapi.exception.NotFoundRentalPropertyException;
import fr.properties.rentpropertiesapi.mapper.RentalPropertyDtoMapper;
import fr.properties.rentpropertiesapi.repository.RentalPropertyRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent-properties-api/rental-properties")
@RequiredArgsConstructor
public class RentalPropertyResource {

    private final RentalPropertyRepository rentalPropertyRepository;
    private final RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RentalPropertyResponseDto> getRentalProperties() {
        List<RentalPropertyEntity> rentalProperties = rentalPropertyRepository.findAll();

        return rentalPropertyDtoMapper.mapToDtoList(rentalProperties);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalPropertyResponseDto getRentalProperty(@PathVariable int id) {
        return rentalPropertyRepository.findById(id)
                .map(rentalPropertyDtoMapper::mapToDto)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien immobilier avec l'id : " + id + " est introuvable"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRentalProperty(@Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto) {
        RentalPropertyEntity rentalPropertyEntity = rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto);

        rentalPropertyRepository.save(rentalPropertyEntity);
    }
}
