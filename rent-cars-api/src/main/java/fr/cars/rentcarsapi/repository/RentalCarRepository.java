package fr.cars.rentcarsapi.repository;

import fr.cars.rentcarsapi.domain.RentalCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalCarRepository extends JpaRepository<RentalCarEntity, Integer> {
}
