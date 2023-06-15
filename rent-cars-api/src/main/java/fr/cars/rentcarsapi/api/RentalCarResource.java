package fr.cars.rentcarsapi.api;

import fr.cars.rentcarsapi.dto.request.RentalCarRequestDto;
import fr.cars.rentcarsapi.dto.request.patch.RentalCarRequestDtoPatch;
import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;
import fr.cars.rentcarsapi.service.RentalCarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent-cars-api/rental-cars")
@RequiredArgsConstructor
public class RentalCarResource {

    private final RentalCarService rentalCarService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RentalCarResponseDto> getRentalCars() {
        return rentalCarService.getRentalCars();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalCarResponseDto getRentalCar(@PathVariable int id) {
        return rentalCarService.getRentalCar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRentalCar(@RequestBody @Valid RentalCarRequestDto rentalCarRequestDto) {
        rentalCarService.createRentalCar(rentalCarRequestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRentalCar(
            @RequestBody @Valid RentalCarRequestDto rentalCarRequestDto,
            @PathVariable int id
    ) {
        rentalCarService.updateRentalCar(id, rentalCarRequestDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void patchRentalCar(
            @RequestBody @Valid RentalCarRequestDtoPatch rentalCarRequestDtoPatch,
            @PathVariable int id
    ) {
        rentalCarService.patchRentalCar(id, rentalCarRequestDtoPatch);
    }
}
