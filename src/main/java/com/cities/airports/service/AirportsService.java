package com.cities.airports.service;

import java.util.*;

import com.cities.airports.model.Airports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// tag to init Service class
@Service
public class AirportsService {


    // Создание логгера
    private static final Logger logger = LoggerFactory.getLogger(AirportsService.class);

    private Map<String, ArrayList<Airports>> cities = new HashMap<String, ArrayList<Airports>>();;

    public ResponseEntity<Airports> getAirports(
            String cityName,
            String airportName
    ) {

        Airports foundAirports = null;

        if(cities.containsKey(cityName)) {
            ArrayList<Airports> cityAirports = cities.get(cityName);
            for(Airports airports: cityAirports) {
                if(airportName.equals(airports.getAirportName())) {
                    foundAirports = airports;  // Сохраняем найденный объект
                }
            }
        }

        if(foundAirports == null) {
            logger.info("GETTING: airport not found");
            return ResponseEntity.badRequest().body(foundAirports);
        }
        return ResponseEntity.ok(foundAirports);
    }

    public ResponseEntity<String> createAirports(Airports airports, String cityName){

        // lazy id adding
        airports.setId(new Random().nextInt(1000));

        if(cities.containsKey(cityName)) {
            ArrayList<Airports> cityAirports = cities.get(cityName);

            for (Airports cityAirport : cityAirports) {
                if (airports.getAirportName().equals(cityAirport.getAirportName())) {
                    logger.info("POSTING: airport already exist");
                    return ResponseEntity.badRequest().body("This airport is already in list!!!");
                }
            }

            cityAirports.add(airports);
        } else {
            ArrayList<Airports> cityAirports = new ArrayList<>();
            cityAirports.add(airports);
            cities.put(cityName, cityAirports);
        }
        return ResponseEntity.ok("Airport add in city list!!");
    }

    public ResponseEntity<String> putAirports(
            String cityName,
            String airportName,
            Airports airports
    ) {
       if (cities.containsKey(cityName)) {
            ArrayList<Airports> cityAirports = cities.get(cityName);

            // Поиск и замена с использованием ListIterator
            ListIterator<Airports> iterator = cityAirports.listIterator();
            boolean setFlag = false;
            while (iterator.hasNext()) {
                Airports currentAirport = iterator.next();

                if (airportName.equals(currentAirport.getAirportName())) {
                    // Заменяем найденный объект
                    iterator.set(airports);
                    setFlag = true;
                    break;
                }
            }
            if (setFlag) {
                return ResponseEntity.ok("Succesfully putting!!");
            }
            logger.info("PUTTING: airport not found");
        }
        return ResponseEntity.badRequest().body("No city or airport yet!!");
    }


    public ResponseEntity<String> deleteAirports(
            String cityName,
            String airportName
    ) {
        if (cities.containsKey(cityName)) {
            ArrayList<Airports> cityAirports = cities.get(cityName);

            boolean deleteFlag = false;

            // Удаление объекта
            for (int i = 0; i < cityAirports.size(); i++) {
                if (airportName.equals(cityAirports.get(i).getAirportName())) {
                    cityAirports.remove(i);
                    deleteFlag = true;
                    break;  // Выходим после удаления, чтобы избежать ConcurrentModificationException
                }
            }
            if (deleteFlag) {
                return ResponseEntity.ok("Succesfully deleting!!");
            }
            logger.info("DELETING: airport not found");
        }
        return ResponseEntity.badRequest().body("No city or airport yet!!");
    }
}

