package fr.properties.rentpropertiesapi.api;

import java.util.List;

import fr.properties.rentpropertiesapi.dto.request.RentalPropertyRequestDto;
import fr.properties.rentpropertiesapi.dto.request.patch.RentalPropertyRequestDtoPatch;
import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;
import fr.properties.rentpropertiesapi.service.RentalPropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent-properties-api/rental-properties")
@RequiredArgsConstructor
public class RentalPropertyResource {

    private final RentalPropertyService rentalPropertyService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RentalPropertyResponseDto> getRentalProperties() {
        return rentalPropertyService.getRentalProperties();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalPropertyResponseDto getRentalProperty(@PathVariable int id) {
        return rentalPropertyService.getRentalProperty(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRentalProperty(@Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto) {
        rentalPropertyService.createRentalProperty(rentalPropertyRequestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRentalProperty(
            @PathVariable int id,
            @Valid @RequestBody RentalPropertyRequestDto rentalPropertyRequestDto
    ) {
        rentalPropertyService.updateRentalProperty(id, rentalPropertyRequestDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void patchRentalProperty(
            @PathVariable int id,
            @Valid @RequestBody RentalPropertyRequestDtoPatch propertyRequestDtoPatch
    ) {
        rentalPropertyService.patchRentalProperty(id, propertyRequestDtoPatch);
    }
}
