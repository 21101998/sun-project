package com.martynenko.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martynenko.spring.datajpa.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
	List<City> findByNameCityContaining(String nameCity);
}
