package fr.properties.rentpropertiesapi.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "property_type")
@Data
public class PropertyTypeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "designation", nullable = false, length = 5)
    private String designation;
}
