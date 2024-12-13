package com.cities.airports.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@Entity
@Table(name="airports")
public class Airports extends RepresentationModel<Airports> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "\"cityName\"", nullable = false)
    private String cityName;

    @Column(name = "\"airportName\"", nullable = false)
    private String airportName;

    @Column(name = "\"staffCosts\"", nullable = false)
    private int staffCosts;

    @Column(name = "\"revenues\"", nullable = false)
    private int revenues;

    @Column(name = "\"terminalCapacity\"", nullable = false)
    private int terminalCapacity;

    @Column(name = "\"annualPassengerVolume\"", nullable = false)
    private int annualPassengerVolume;

    @Column(name = "\"cargo\"", nullable = false)
    private int cargo;

    private String comment;
    public com.cities.airports.model.Airports withComment(String comment) {
        this.setComment(comment);
        return this;
    }
}
