package fr.properties.rentpropertiesapi.samples;

import java.util.List;

import fr.properties.rentpropertiesapi.domain.EnergyClassificationEntity;
import fr.properties.rentpropertiesapi.domain.PropertyTypeEntity;
import fr.properties.rentpropertiesapi.domain.RentalPropertyEntity;

public class RentalPropertyEntitySample {

    public static RentalPropertyEntity oneRentalPropertyEntity() {
        return new RentalPropertyEntity(
                1,
                "Appartement spacieux avec vue sur l'ESGI",
                "Paris",
                "77 Rue des roses",
                new PropertyTypeEntity(1, "Appartement"),
                750.90,
                1200.90,
                37.48,
                2,
                1,
                3,
                "1990",
                new EnergyClassificationEntity(1, "B"),
                false,
                false,
                true,
                false);
    }

    public static List<RentalPropertyEntity> rentalPropertyEntities() {
        RentalPropertyEntity rentalProperty = oneRentalPropertyEntity();

        return List.of(rentalProperty);
    }
}
