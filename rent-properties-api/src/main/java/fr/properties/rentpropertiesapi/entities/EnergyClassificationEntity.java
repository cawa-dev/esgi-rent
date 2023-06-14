package fr.properties.rentpropertiesapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "energy_classification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnergyClassificationEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "designation", nullable = false, length = -1)
    private String designation;
}
