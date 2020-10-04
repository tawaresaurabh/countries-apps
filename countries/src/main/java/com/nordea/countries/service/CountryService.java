package com.nordea.countries.service;

import com.nordea.countries.dto.Countries;
import com.nordea.countries.dto.Country;
import com.nordea.countries.dto.CountryDetail;
import com.nordea.countries.exceptions.CountriesServiceException;
import com.nordea.countries.exceptions.CountryNotFoundException;

import java.util.LinkedHashMap;
import java.util.List;

public interface CountryService {
    Country populateCountry(LinkedHashMap<String,String> responseMap);
    CountryDetail populateCountryDetail(LinkedHashMap<String,Object> responseMap);
    Countries populateCountries(List<Country> countryList);
    CountriesServiceException generateCountiesServiceException();
    CountryNotFoundException generateCountryNotFoundException();

}
