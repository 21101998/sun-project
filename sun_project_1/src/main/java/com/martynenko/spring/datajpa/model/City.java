package com.martynenko.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name_city")
	private String nameCity;

	@Column(name = "latitude")
	private Double latCity;

	@Column(name = "longitude")
	private Double longCity;

	public City() {

	}


	public City(String nameCity, Double latCity, Double longCity) {
		super();
		this.nameCity = nameCity;
		this.latCity = latCity;
		this.longCity = longCity;
	}





	public long getId() {
		return id;
	}

	public String getNameCity() {
		return nameCity;
	}
	
	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}
	
	public Double getLatCity() {
		return latCity;
	}
	
	public void setLatCity(Double latCity) {
		this.latCity = latCity;
	}
	
	public Double getLongCity() {
		return longCity;
	}
	
	public void setLongCity(Double longCity) {
		this.longCity = longCity;
	}


	@Override
	public String toString() {
		return "City [id=" + id + ", nameCity=" + nameCity + ", latCity=" + latCity + ", longCity=" + longCity + "]";
	}
	
	
	

}
