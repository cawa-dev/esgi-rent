package fr.cars.rentcarsapi.service;

import fr.cars.rentcarsapi.domain.RentalCarEntity;
import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;
import fr.cars.rentcarsapi.exception.NotFoundRentalCarException;
import fr.cars.rentcarsapi.mapper.RentalCarMapper;
import fr.cars.rentcarsapi.repository.RentalCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalCarService {

    private final RentalCarRepository rentalCarRepository;
    private final RentalCarMapper rentalCarMapper;


    public List<RentalCarResponseDto> getRentalCars() {
        List<RentalCarEntity> rentalCarEntities = rentalCarRepository.findAll();

        return rentalCarMapper.mapToDtoList(rentalCarEntities);
    }

    public RentalCarResponseDto getRentalCar(int id) {
        return rentalCarRepository.findById(id)
                .map(rentalCarMapper::mapToDto)
                .orElseThrow(() -> new NotFoundRentalCarException("Le véhicule avec l'id : " + id + " est introuvable"));
    }
}
