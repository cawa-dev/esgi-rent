package fr.properties.rentpropertiesapi.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rental_property")
@Data
public class RentalPropertyEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Basic
    @Column(name = "town", nullable = false, length = 100)
    private String town;

    @Basic
    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_type_id", nullable = false)
    private PropertyTypeEntity propertyType;

    @Basic
    @Column(name = "rent_amount", nullable = false)
    private double rentAmount;

    @Basic
    @Column(name = "security_deposit_amount", nullable = false)
    private double securityDepositAmount;

    @Basic
    @Column(name = "area", nullable = false)
    private double area;

    @Basic
    @Column(name = "number_of_bedrooms")
    private int numberOfBedrooms;

    @Basic
    @Column(name = "floor_number")
    private int floorNumber;

    @Basic
    @Column(name = "number_of_floors")
    private int numberOfFloors;

    @Basic
    @Column(name = "construction_year", length = 4)
    private String constructionYear;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "energy_classification_id")
    private EnergyClassificationEntity energyClassification;

    @Basic
    @Column(name = "has_elevator")
    private boolean hasElevator;

    @Basic
    @Column(name = "has_intercom")
    private boolean hasIntercom;

    @Basic
    @Column(name = "has_balcony")
    private boolean hasBalcony;

    @Basic
    @Column(name = "has_parking_space")
    private boolean hasParkingSpace;

    public RentalPropertyEntity() {
    }

    public RentalPropertyEntity(
            int id,
            String description,
            String town,
            String address,
            PropertyTypeEntity propertyType,
            double rentAmount,
            double securityDepositAmount,
            double area,
            Integer numberOfBedrooms,
            Integer floorNumber,
            Integer numberOfFloors,
            String constructionYear,
            EnergyClassificationEntity energyClassification,
            Boolean hasElevator,
            Boolean hasIntercom,
            Boolean hasBalcony,
            Boolean hasParkingSpace
    ) {
        this.id = id;
        this.description = description;
        this.town = town;
        this.address = address;
        this.propertyType = propertyType;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.area = area;
        this.numberOfBedrooms = numberOfBedrooms;
        this.floorNumber = floorNumber;
        this.numberOfFloors = numberOfFloors;
        this.constructionYear = constructionYear;
        this.energyClassification = energyClassification;
        this.hasElevator = hasElevator;
        this.hasIntercom = hasIntercom;
        this.hasBalcony = hasBalcony;
        this.hasParkingSpace = hasParkingSpace;
    }

    public RentalPropertyEntity(
            String description,
            String town,
            String address,
            PropertyTypeEntity propertyType,
            double rentAmount,
            double securityDepositAmount,
            double area,
            Integer numberOfBedrooms,
            Integer floorNumber,
            Integer numberOfFloors,
            String constructionYear,
            EnergyClassificationEntity energyClassification,
            Boolean hasElevator,
            Boolean hasIntercom,
            Boolean hasBalcony,
            Boolean hasParkingSpace
    ) {
        this.description = description;
        this.town = town;
        this.address = address;
        this.propertyType = propertyType;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.area = area;
        this.numberOfBedrooms = numberOfBedrooms;
        this.floorNumber = floorNumber;
        this.numberOfFloors = numberOfFloors;
        this.constructionYear = constructionYear;
        this.energyClassification = energyClassification;
        this.hasElevator = hasElevator;
        this.hasIntercom = hasIntercom;
        this.hasBalcony = hasBalcony;
        this.hasParkingSpace = hasParkingSpace;
    }
}
