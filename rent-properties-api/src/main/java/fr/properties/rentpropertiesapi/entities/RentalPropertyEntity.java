package fr.properties.rentpropertiesapi.entities;

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
    private Integer numberOfBedrooms;

    @Basic
    @Column(name = "floor_number")
    private Integer floorNumber;

    @Basic
    @Column(name = "number_of_floors")
    private Integer numberOfFloors;

    @Basic
    @Column(name = "construction_year", length = 4)
    private String constructionYear;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "energy_classification_id")
    private EnergyClassificationEntity energyClassification;

    @Basic
    @Column(name = "has_elevator")
    private Boolean hasElevator;

    @Basic
    @Column(name = "has_intercom")
    private Boolean hasIntercom;

    @Basic
    @Column(name = "has_balcony")
    private Boolean hasBalcony;

    @Basic
    @Column(name = "has_parking_space")
    private Boolean hasParkingSpace;
}
