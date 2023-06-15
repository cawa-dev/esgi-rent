package fr.properties.rentpropertiesapi.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "energy_classification")
@Data
@Builder
public class EnergyClassificationEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "designation", length = -1)
    private String designation;

    public EnergyClassificationEntity() {
    }

    public EnergyClassificationEntity(int id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public EnergyClassificationEntity(String designation) {
        this.designation = designation;
    }
}
