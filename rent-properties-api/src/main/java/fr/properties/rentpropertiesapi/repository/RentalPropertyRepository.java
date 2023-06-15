package fr.properties.rentpropertiesapi.repository;

import fr.properties.rentpropertiesapi.domain.RentalPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalPropertyRepository extends JpaRepository<RentalPropertyEntity, Integer> {
}
