package com.martynenko.spring.datajpa.controller;


import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.contains;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import com.martynenko.spring.datajpa.model.City;


public class UnitTestsController extends AbstractTest{

	private String uri = "http://localhost:8080/api/cities";
		@Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
		
		private static final Logger LOGGER = LogManager.getLogger(CityController.class);
		
		
		//classic ok get_by_list_and_both_event request
		@Test
		public void getSomeCitiesBothEventByList() throws Exception {
			String param = "?nameCities=Odessa,Donetsk&event=both";
		    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+param)
		         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		      
		    int status = mvcResult.getResponse().getStatus();
		    assertEquals(200, status);
		    String content = mvcResult.getResponse().getContentAsString();
		    LOGGER.info("content - "+content);
		    //City[] citieslist = super.mapFromJson(content, City[].class);
		    assertTrue(content.length() > 2);
		    assertThat(content,containsString("both"));
	   }
		
		//classic ok get_by_list_and_sunrise_event request
		@Test
		public void getSomeCitiesSunrEventByList() throws Exception {
			String param = "?nameCities=Odessa,Donetsk&event=sunrise";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+param)
				 .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
				      
			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);
			String content = mvcResult.getResponse().getContentAsString();
			LOGGER.info("content - "+content);
				    //City[] citieslist = super.mapFromJson(content, City[].class);
			assertTrue(content.length() > 2);
			assertThat(content,containsString("sunrise"));
		}
		
		//classic ok get_by_list_and_sunset_event request
		@Test
		public void getSomeCitiesSunsEventByList() throws Exception {
			String param = "?nameCities=Odessa,Donetsk&event=sunset";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+param)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
						      
			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);
			String content = mvcResult.getResponse().getContentAsString();
			LOGGER.info("content - "+content);
						    //City[] citieslist = super.mapFromJson(content, City[].class);
			assertTrue(content.length() > 2);
			assertThat(content,containsString("sunset"));
		}
		
		//204 get_by_list_not_event request
		@Test
		public void getSomeCitiesNotEventByList() throws Exception {
			String param = "?nameCities=Odessa,Donetsk";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+param)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
								      
			int status = mvcResult.getResponse().getStatus();
			assertEquals(204, status);
		}
		
		//204 get_by_event_not_list_city request
		@Test
		public void getSomeCitiesNotCityByEvent() throws Exception {
			String param = "?event=both";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+param)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
										      
			int status = mvcResult.getResponse().getStatus();
			assertEquals(204, status);
		}
		
		//204 get_by_list_empty_event request
		@Test
		public void getSomeCitiesEmptyEventByList() throws Exception {
			String param = "?nameCities=Odessa,Donetsk&event=";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+param)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
										      
			int status = mvcResult.getResponse().getStatus();
			assertEquals(204, status);
		}
				
		//204 get_by_event_empty_list_city request
		@Test
		public void getSomeCitiesEmptyCityByEvent() throws Exception {
			String param = "?nameCities=&event=sunset";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+param)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
												      
			int status = mvcResult.getResponse().getStatus();
			assertEquals(204, status);
		}
		
		//204 get_by_empty_event_empty_list_city request
		@Test
		public void getSomeCitiesEmptyCityEmptyEvent() throws Exception {
			String param = "?nameCities=&event=";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+param)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
														      
			int status = mvcResult.getResponse().getStatus();
			assertEquals(204, status);
		}
		
		//204 get_by_not param request
		@Test
		public void getSomeCitiesNotParam() throws Exception {
			String param = "?";
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+param)
					.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
																      
			int status = mvcResult.getResponse().getStatus();
			assertEquals(204, status);
		}
		
		//204 get not found city
			@Test
			public void getSomeCitiesNotFoundResult() throws Exception {
				String param = "?nameCities=Melitopol,Nikolaev&event=both";
				MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+param)
				         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
				      
				int status = mvcResult.getResponse().getStatus();
				assertEquals(204, status);
			}
		
	  //classic ok get_all request
	   @Test
	   public void getAllCitiesList() throws Exception {
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+"/all")
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      LOGGER.info("content - "+content);
	      City[] citieslist = super.mapFromJson(content, City[].class);
	      assertTrue(citieslist.length > 0);
	   }
	   
	   //post--------------------------------------
	   //classic ok request, all param exist
	   @Test
	   public void postNewCity() throws Exception {
	      City city = new City();
	      city.setNameCity("Kharkiv");
	      city.setLatCity(49.98);
	      city.setLongCity(36.25);
	      
	      String inputJson = super.mapToJson(city);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      LOGGER.info("status - "+status);
	      assertEquals(201, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      City create_city = super.mapFromJson(content, City.class);
	      assertEquals(city.getNameCity(), create_city.getNameCity());
	   }
	   
	   
	   //400 request, name param not exist
	   @Test
	   public void postNewCityNoName() throws Exception {
	      City city = new City();
	      city.setLatCity(49.98);
	      city.setLongCity(36.25);
	      
	      String inputJson = super.mapToJson(city);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      LOGGER.info("status - "+status);
	      assertEquals(400, status);
	   }
	
	 //400 request, latitude param not exist
	   @Test
	   public void postNewCityNoLat() throws Exception {
	      City city = new City();
	      city.setNameCity("Kharkiv");
	      city.setLongCity(36.25);
	      
	      String inputJson = super.mapToJson(city);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      LOGGER.info("status - "+status);
	      assertEquals(400, status);
	   }
	   
	 //400 request, longitude param not exist
	   @Test
	   public void postNewCityNoLong() throws Exception {
	      City city = new City();
	      city.setNameCity("Kharkiv");
	      city.setLatCity(49.98);
	      
	      String inputJson = super.mapToJson(city);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      LOGGER.info("status - "+status);
	      assertEquals(400, status);
	   }
	   
	  //400 request, name param empty
	   @Test
	   public void postNewCityNameEmpty() throws Exception {
	      City city = new City();
	      city.setNameCity("");
	      city.setLatCity(49.98);
	      city.setLongCity(36.25);
	      
	      String inputJson = super.mapToJson(city);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      LOGGER.info("status - "+status);
	      assertEquals(400, status);
	   }
	
	 //400 request, all params empty
	   @Test
	   public void postNewCityAllParamEmpty() throws Exception {
	      City city = new City();
	      
	      String inputJson = super.mapToJson(city);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      LOGGER.info("status - "+status);
	      assertEquals(400, status);
	   }
	   
	 //400 request, all params empty
	   @Test
	   public void postNewCityNoParam() throws Exception {
	      //City city = new City();
	      
	      //String inputJson = super.mapToJson(city);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	    		  .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      LOGGER.info("status - "+status);
	      assertEquals(400, status);
	   }
}
