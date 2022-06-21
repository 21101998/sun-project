package com.martynenko.spring.datajpa.response_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//external server response api 
//https://api.sunrise-sunset.org/json?lat=36.7201600&lng=-4.4203400
/*example 
  {
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
    },
    "status": "OK"
  }
  */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonModelExternalResponse {
	private ModelDataResults results;
	private String status;
	
	public CommonModelExternalResponse() {
	}
	
	public ModelDataResults getResults() {
		return results;
	}
	
	public void setResults(ModelDataResults results) {
		this.results = results;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CommonModelExternalResponse [status=" + status + " results=" + results + "]";
	}
	
	
	

}
