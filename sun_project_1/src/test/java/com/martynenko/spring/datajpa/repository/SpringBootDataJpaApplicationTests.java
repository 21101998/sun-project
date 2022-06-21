package com.martynenko.spring.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.martynenko.spring.datajpa.model.City;
import com.martynenko.spring.datajpa.repository.CityRepository;



@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class SpringBootDataJpaApplicationTests {

	
	@Autowired
	CityRepository cRepo;
	
	@Test
	@Order(1)
	public void testRepoCreateRecord () {
		City city = new City();
		city.setNameCity("Uman");
		city.setLatCity(48.75);
		city.setLongCity(30.22);
		cRepo.save(city);
		assertNotNull(cRepo.findByNameCityContaining("Uman").get(0));
	}
		
	@Test
	@Order(2)
	public void testRepoReadAllRecords () {
		List<City> list = cRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
		
	@Test
	@Order(3)
	public void testRepoReadRecord () {
		City city = cRepo.findByNameCityContaining("Uman").get(0);
		assertEquals("Uman", city.getNameCity());
	}
		
	

}
