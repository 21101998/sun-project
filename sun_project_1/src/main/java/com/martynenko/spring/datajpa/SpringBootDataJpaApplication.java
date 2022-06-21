package com.martynenko.spring.datajpa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.martynenko.spring.datajpa.controller.CityController;
import com.martynenko.spring.datajpa.response_model.CommonModelExternalResponse;

@SpringBootApplication
public class SpringBootDataJpaApplication 
{

	private static final Logger LOGGER = LogManager.getLogger(SpringBootDataJpaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
		
	}

}
