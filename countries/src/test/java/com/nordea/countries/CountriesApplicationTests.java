package com.nordea.countries;

import com.nordea.countries.controllers.CountriesController;

import com.nordea.countries.exceptions.CountriesServiceException;
import com.nordea.countries.exceptions.CountryNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CountriesController.class)
class CountriesApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	private final String JSON_RESPONSE  = "{\"name\":\"India\",\"capital\":\"New Delhi\",\"population\":1295210000,\"country_code\":\"IN\",\"flag_file_url\":\"https://restcountries.eu/data/ind.svg\"}";

	@Test
	public void getCountriesEndPointTest() throws Exception {
		this.mockMvc.perform(get("/countries/"))
				.andExpect(status().isOk());
	}

	@Test
	public void getCountryEndPointTest() throws Exception {
		this.mockMvc.perform(get("/countries/{name}","India"))
				.andExpect(status().isOk())
				.andExpect(result -> assertEquals(JSON_RESPONSE, result.getResponse().getContentAsString()));
		;
	}

	@Test
	public void getInvalidCountryEndPointTest() throws Exception {
		this.mockMvc.perform(get("/countries/{name}", "Ind"))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof CountryNotFoundException))
				.andExpect(result -> assertEquals("Country data not found", result.getResolvedException().getMessage()));
	}


	@Test
	//Test needed to be done while being disconnected from the internet
	public void getServerNotConnectedException() throws Exception {
		this.mockMvc.perform(get("/countries/{name}", "India"))
				.andExpect(status().isInternalServerError())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof CountriesServiceException))
				.andExpect(result -> assertEquals("Error fetching data from external countries service", result.getResolvedException().getMessage()));
	}


}
