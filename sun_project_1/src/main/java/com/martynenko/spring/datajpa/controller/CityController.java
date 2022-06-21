package com.martynenko.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.martynenko.spring.datajpa.SpringBootDataJpaApplication;
import com.martynenko.spring.datajpa.model.City;
import com.martynenko.spring.datajpa.repository.CityRepository;
import com.martynenko.spring.datajpa.response_model.CommonModelExternalResponse;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CityController {

	@Autowired
	CityRepository cityRepository;

	
	private static final Logger LOGGER = LogManager.getLogger(CityController.class);
	
	@GetMapping("/cities")
	public ResponseEntity<List<Map<String,Object>>> getCitiesByListName(String[] nameCities, String event) {
		try {
			List<City> cities = new ArrayList<City>();
			if (nameCities==null||event==null) {//not find nameCities parameter or event parametr
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				if(nameCities.length>0&&validEvent(event)) {//cities list is here in params
					for(int i=0;i<nameCities.length;i++) {
						cityRepository.findByNameCityContaining(nameCities[i]).forEach(cities::add);
					}
				}else {//nameCities parameter is empty
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
					
			}

			if(cities.size()>0) {//if result find
				//----------------------------------
				RestTemplate restTemplate = new RestTemplate();
				List<Map<String,Object>> result_list = new ArrayList<Map<String,Object>>();
				
				for(City city:cities) {
					CommonModelExternalResponse commonModelExternalResponse = restTemplate.getForObject(
							"https://api.sunrise-sunset.org/json?lat="+String.valueOf(city.getLatCity())
							+ "&lng=" + String.valueOf(city.getLongCity()), 
							CommonModelExternalResponse.class);
					if(commonModelExternalResponse.getStatus().equals("OK")) {//if external server is ok
						Map<String, Object> paramsMap = new HashMap<>();
						paramsMap.put("nameCity", city.getNameCity());
				        paramsMap.put("typeEvent", event);
				        
				        switch(event) {
							case "sunrise": {
						        paramsMap.put("timeEvent", commonModelExternalResponse.getResults().getSunrise());
						        break;
					        }
							case "sunset": {
								paramsMap.put("timeEvent", commonModelExternalResponse.getResults().getSunset());
								break;
					        }
							case "both": {
								paramsMap.put("timeSunriseEvent", commonModelExternalResponse.getResults().getSunrise());
						        paramsMap.put("timeSunsetEvent", commonModelExternalResponse.getResults().getSunset());
						        break;
							}
				        }
				        result_list.add(paramsMap);
					}
				}
				
				if(result_list.size()>0) {//some city info here
					return new ResponseEntity<>(result_list, HttpStatus.OK);
				}else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			LOGGER.info("Exeption in CityController.getCitiesByListName - " + e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cities/all")
	public ResponseEntity<List<City>> getAllCities() {
		try {
			List<City> cities = new ArrayList<City>();
			cityRepository.findAll().forEach(cities::add);
			
			if(cities.size()>0) {
				return new ResponseEntity<>(cities, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			LOGGER.info("Exeption in CityController.getAllCities - " + e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	@PostMapping("/cities")
	public ResponseEntity<City> createCity(@RequestBody City city) {
		if(validCity(city)&&validLat(city.getLatCity())&&validLong(city.getLongCity())) {
			try {
				City _city = cityRepository
						.save(new City(city.getNameCity(), city.getLatCity(), city.getLongCity()));
				LOGGER.info("Adding new city - "+_city.toString());
				return new ResponseEntity<>(_city, HttpStatus.CREATED);
			} catch (Exception e) {
				LOGGER.info("Exeption in CityController.createCity - " + e.toString());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	private boolean validCity(City city) {
		if((city.getNameCity()!=null)
				&&(city!=null)
				&&(city.getLatCity()!=null)
				&&(city.getLongCity()!=null)
				&&(city.getNameCity()!="")) {
			return true;
		}
		return false;
	}
	
	private boolean validEvent(String event) {
		if(event.equals("sunrise")||event.equals("sunset")||event.equals("both")) {
			return true;
		}
		return false;
	}
	
	private boolean validLat(Double latitude) {
		if((latitude<90)&&(latitude>-90)) {return true;}
		return false;
	}
	
	private boolean validLong(Double longitude) {
		if((longitude<180)&&(longitude>-180)) {return true;}
		return false;
	}
	


	@DeleteMapping("/cities")
	public ResponseEntity<HttpStatus> deleteAllcities() {
		try {
			cityRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info("Exeption in CityController.deleteAllcities - " + e.toString());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}
