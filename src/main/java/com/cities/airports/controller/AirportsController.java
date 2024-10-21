package com.cities.airports.controller;

import com.cities.airports.model.Airports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cities.airports.service.AirportsService;

//tag to init Rest controller
@RestController
@RequestMapping(value="cities/{cityName}/airports")
public class AirportsController {

    @Autowired
    private AirportsService airportsService;

    @GetMapping(value="/{airportName}")
    public ResponseEntity<Airports> getAirports(
            @PathVariable("cityName") String cityName,
            @PathVariable("airportName") String airportName
    ) {
        return airportsService.getAirports(cityName, airportName);
    }

    @PostMapping
    public ResponseEntity<String> createAirports(
            @PathVariable("cityName") String cityName,
            @RequestBody Airports request
    ) {
        return airportsService.createAirports(request, cityName);
    }

    @PutMapping(value="/{airportName}")
    public ResponseEntity<String> putAirports(
            @PathVariable("cityName") String cityName,
            @PathVariable("airportName") String airportName,
            @RequestBody Airports request
    ) {
        return airportsService.putAirports(cityName, airportName, request);
    }

    @DeleteMapping(value="/{airportName}")
    public ResponseEntity<String> deleteAirports(
            @PathVariable("cityName") String cityName,
            @PathVariable("airportName") String airportName
    ) {
        return airportsService.deleteAirports(cityName, airportName);
    }

}
