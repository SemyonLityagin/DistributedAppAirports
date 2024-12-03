package com.cities.airports.repository;

import com.cities.airports.model.Airports;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

@Repository
public interface AirportsRepository
        extends CrudRepository<Airports,String> {
    public List<Airports> findByAirportName
            (String airportName);
    public Airports findByCityNameAndAirportName
            (String cityName,
             String airportName);
}