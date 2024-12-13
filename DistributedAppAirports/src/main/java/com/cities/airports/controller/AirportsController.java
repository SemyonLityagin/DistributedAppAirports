package com.cities.airports.controller;

import com.cities.airports.model.Airports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cities.airports.service.AirportsService;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//tag to init Rest controller
@RestController
@RequestMapping(value="cities/{cityName}/airports")
public class AirportsController {

    @Autowired
    private AirportsService airportsService;

    @Autowired
    MessageSource messages;

    @GetMapping(value="/{airportName}")
    public ResponseEntity<Airports> getAirports(
            @PathVariable("cityName") String cityName,
            @PathVariable("airportName") String airportName,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale
    ) {
        ResponseEntity<Airports> response = airportsService.getAirports(cityName, airportName, locale);
        Airports airports = response.getBody();
        if(airports != null){
            airports.add(linkTo(methodOn(AirportsController.class)
                            .getAirports(cityName, airportName, null))
                            .withSelfRel(),
                    linkTo(methodOn(AirportsController.class)
                            .createAirports(cityName, airports, null))
                            .withRel(messages.getMessage("airport.create.URL.name", null,locale)),
                    linkTo(methodOn(AirportsController.class)
                            .putAirports(cityName, airportName, airports, null))
                            .withRel(messages.getMessage("airport.put.URL.name", null,locale)),
                    linkTo(methodOn(AirportsController.class)
                            .deleteAirports(cityName, airportName, null))
                            .withRel(messages.getMessage("airport.delete.URL.name",null,locale))
            );
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<String> createAirports(
            @PathVariable("cityName") String cityName,
            @RequestBody Airports request,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale
    ) {
        return airportsService.createAirports(request, cityName, locale);
    }

    @PutMapping(value="/{airportName}")
    public ResponseEntity<String> putAirports(
            @PathVariable("cityName") String cityName,
            @PathVariable("airportName") String airportName,
            @RequestBody Airports request,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale
    ) {
        return airportsService.putAirports(cityName, airportName, request, locale);
    }

    @DeleteMapping(value="/{airportName}")
    public ResponseEntity<String> deleteAirports(
            @PathVariable("cityName") String cityName,
            @PathVariable("airportName") String airportName,
            @RequestHeader(value = "Accept-Language",required = false) Locale locale
    ) {
        return airportsService.deleteAirports(cityName, airportName, locale);
    }

}
