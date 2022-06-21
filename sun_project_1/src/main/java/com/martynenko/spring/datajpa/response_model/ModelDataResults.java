package com.martynenko.spring.datajpa.response_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//external server response api results
//https://api.sunrise-sunset.org/json?lat=36.7201600&lng=-4.4203400
/*example 
"results": {
      "sunrise": "4:57:29 AM",
      "sunset": "7:41:03 PM",
      "solar_noon": "12:19:16 PM",
      "day_length": "14:43:34",
      "civil_twilight_begin": "4:28:19 AM",
      "civil_twilight_end": "8:10:14 PM",
      "nautical_twilight_begin": "3:50:04 AM",
      "nautical_twilight_end": "8:48:29 PM",
      "astronomical_twilight_begin": "3:07:09 AM",
      "astronomical_twilight_end": "9:31:23 PM"
  }
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelDataResults {
	
	private String sunrise;
	private String sunset;
	private String solar_noon;
	private String day_length;
	private String civil_twilight_begin;
	private String civil_twilight_end;
	private String nautical_twilight_begin;
	private String nautical_twilight_end;
	private String astronomical_twilight_begin;
	private String astronomical_twilight_end;
	
	public ModelDataResults() {
		
	}
	
	public String getSunrise() {
		return sunrise;
	}
	
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	
	public String getSunset() {
		return sunset;
	}
	
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	
	public String getSolar_noon() {
		return solar_noon;
	}
	
	public void setSolar_noon(String solar_noon) {
		this.solar_noon = solar_noon;
	}
	
	public String getDay_length() {
		return day_length;
	}
	
	public void setDay_length(String day_length) {
		this.day_length = day_length;
	}
	
	public String getCivil_twilight_begin() {
		return civil_twilight_begin;
	}
	
	public void setCivil_twilight_begin(String civil_twilight_begin) {
		this.civil_twilight_begin = civil_twilight_begin;
	}
	
	public String getCivil_twilight_end() {
		return civil_twilight_end;
	}
	
	public void setCivil_twilight_end(String civil_twilight_end) {
		this.civil_twilight_end = civil_twilight_end;
	}
	
	public String getNautical_twilight_begin() {
		return nautical_twilight_begin;
	}
	
	public void setNautical_twilight_begin(String nautical_twilight_begin) {
		this.nautical_twilight_begin = nautical_twilight_begin;
	}
	
	public String getNautical_twilight_end() {
		return nautical_twilight_end;
	}
	
	public void setNautical_twilight_end(String nautical_twilight_end) {
		this.nautical_twilight_end = nautical_twilight_end;
	}
	
	public String getAstronomical_twilight_begin() {
		return astronomical_twilight_begin;
	}
	
	public void setAstronomical_twilight_begin(String astronomical_twilight_begin) {
		this.astronomical_twilight_begin = astronomical_twilight_begin;
	}
	
	public String getAstronomical_twilight_end() {
		return astronomical_twilight_end;
	}
	
	public void setAstronomical_twilight_end(String astronomical_twilight_end) {
		this.astronomical_twilight_end = astronomical_twilight_end;
	}

	@Override
	public String toString() {
		return "ModelDataResults [sunrise=" + sunrise + ", sunset=" + sunset + ", solar_noon=" + solar_noon
				+ ", day_length=" + day_length + ", civil_twilight_begin=" + civil_twilight_begin
				+ ", civil_twilight_end=" + civil_twilight_end + ", nautical_twilight_begin=" + nautical_twilight_begin
				+ ", nautical_twilight_end=" + nautical_twilight_end + ", astronomical_twilight_begin="
				+ astronomical_twilight_begin + ", astronomical_twilight_end=" + astronomical_twilight_end + "]";
	}

	
	
}
