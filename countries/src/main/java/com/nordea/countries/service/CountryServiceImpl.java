package com.nordea.countries.service;

import com.nordea.countries.dto.Countries;
import com.nordea.countries.dto.Country;
import com.nordea.countries.dto.CountryDetail;
import com.nordea.countries.exceptions.CountriesServiceException;
import com.nordea.countries.exceptions.CountryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{
    @Override
    public Country populateCountry(LinkedHashMap<String, String> responseMap) {
        String name =  responseMap.get("name");
        String alpha2Code = responseMap.get("alpha2Code");
        return new Country(name,alpha2Code);
    }

    @Override
    public CountryDetail populateCountryDetail(LinkedHashMap<String, Object> responseMap) {
        String name = (String) responseMap.get("name");
        String alpha2Code = (String) responseMap.get("alpha2Code");
        String capital = (String) responseMap.get("capital");
        String flag = (String) responseMap.get("flag");
        Integer population = (Integer) responseMap.get("population");
        CountryDetail countryDetail = new CountryDetail(name, alpha2Code);
        countryDetail.setCapital(capital);
        countryDetail.setFlagFileUrl(flag);
        countryDetail.setPopulation(population);
        return countryDetail;
    }

    @Override
    public Countries populateCountries(List<Country> countryList) {
        Countries countries = new Countries();
        countries.setCountries(countryList);
        return countries;
    }

    @Override
    public CountriesServiceException generateCountiesServiceException() {
        return new CountriesServiceException("Error fetching data from external countries service");
    }

    @Override
    public CountryNotFoundException generateCountryNotFoundException() {
        return new CountryNotFoundException("Country data not found");
    }
}
