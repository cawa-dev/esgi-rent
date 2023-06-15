package fr.cars.rentcarsapi.service;

import fr.cars.rentcarsapi.domain.RentalCarEntity;
import fr.cars.rentcarsapi.dto.request.RentalCarRequestDto;
import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;
import fr.cars.rentcarsapi.exception.NotFoundRentalCarException;
import fr.cars.rentcarsapi.mapper.RentalCarMapper;
import fr.cars.rentcarsapi.repository.RentalCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void createRentalCar(RentalCarRequestDto rentalCarRequestDto) {
        RentalCarEntity rentalCarEntity = rentalCarMapper.mapToEntity(rentalCarRequestDto);

        rentalCarRepository.save(rentalCarEntity);
    }

    public void updateRentalCar(int id, RentalCarRequestDto rentalCarRequestDto) {
        Optional<RentalCarEntity> optionalExistingRentalCar = rentalCarRepository.findById(id);

        if (optionalExistingRentalCar.isPresent()) {
            RentalCarEntity existingRentalCar = optionalExistingRentalCar.get();
            existingRentalCar.setBrand(rentalCarRequestDto.brand());
            existingRentalCar.setModel(rentalCarRequestDto.model());
            existingRentalCar.setRentAmount(rentalCarRequestDto.rentAmount());
            existingRentalCar.setSecurityDepositAmount(rentalCarRequestDto.securityDepositAmount());
            existingRentalCar.setNumberOfSeats(rentalCarRequestDto.numberOfSeats());
            existingRentalCar.setNumberOfDoors(rentalCarRequestDto.numberOfDoors());
            existingRentalCar.setHasAirConditioning(rentalCarRequestDto.hasAirConditioning());

            rentalCarRepository.save(existingRentalCar);
        } else {
            RentalCarEntity newRentalCar = rentalCarMapper.mapToEntity(rentalCarRequestDto);

            rentalCarRepository.save(newRentalCar);
        }
    }
}
