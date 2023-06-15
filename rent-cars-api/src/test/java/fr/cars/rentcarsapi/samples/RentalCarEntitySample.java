package fr.cars.rentcarsapi.samples;

import fr.cars.rentcarsapi.domain.RentalCarEntity;

import java.util.List;

public class RentalCarEntitySample {

    public static RentalCarEntity oneRentalCarEntity() {
        return new RentalCarEntity(
                "BMW",
                "Serie 1",
                790.9,
                1550.9,
                5,
                4,
                true);
    }

    public static List<RentalCarEntity> rentalCarEntities() {
        RentalCarEntity rentalCarEntity = oneRentalCarEntity();

        return List.of(rentalCarEntity);
    }
}
