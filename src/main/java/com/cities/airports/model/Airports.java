package com.cities.airports.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
public class Airports extends RepresentationModel<Airports> {
    private int id;
    private String cityName;
    private String airportName;
    private int staffCosts;
    private int revenues;
    private int terminalCapacity;
    private int annualPassengerVolume;
    private int cargo;
}
