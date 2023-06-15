package fr.cars.rentcarsapi.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rental_car")
@Data
public class RentalCarEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    @Basic
    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Basic
    @Column(name = "rent_amount", nullable = false)
    private double rentAmount;

    @Basic
    @Column(name = "security_deposit_amount", nullable = false)
    private double securityDepositAmount;

    @Basic
    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Basic
    @Column(name = "number_of_doors")
    private Integer numberOfDoors;

    @Basic
    @Column(name = "has_air_conditioning")
    private Boolean hasAirConditioning;

    public RentalCarEntity() {
    }

    public RentalCarEntity(
            int id,
            String brand,
            String model,
            double rentAmount,
            double securityDepositAmount,
            Integer numberOfSeats,
            Integer numberOfDoors,
            Boolean hasAirConditioning
    ) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.numberOfSeats = numberOfSeats;
        this.numberOfDoors = numberOfDoors;
        this.hasAirConditioning = hasAirConditioning;
    }

    public RentalCarEntity(
            String brand,
            String model,
            double rentAmount,
            double securityDepositAmount,
            Integer numberOfSeats,
            Integer numberOfDoors,
            Boolean hasAirConditioning
    ) {
        this.brand = brand;
        this.model = model;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.numberOfSeats = numberOfSeats;
        this.numberOfDoors = numberOfDoors;
        this.hasAirConditioning = hasAirConditioning;
    }
}
