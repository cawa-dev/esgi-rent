package fr.properties.rentpropertiesapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "property_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTypeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "designation", nullable = false, length = 5)
    private String designation;
}
