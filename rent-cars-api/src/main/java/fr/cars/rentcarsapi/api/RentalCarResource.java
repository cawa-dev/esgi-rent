package fr.cars.rentcarsapi.api;

import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;
import fr.cars.rentcarsapi.service.RentalCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

}
