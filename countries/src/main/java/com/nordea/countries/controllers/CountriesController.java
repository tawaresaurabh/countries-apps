package com.nordea.countries.controllers;




import com.nordea.countries.dto.Countries;
import com.nordea.countries.dto.Country;
import com.nordea.countries.dto.CountryDetail;
import com.nordea.countries.exceptions.CountryNotFoundException;
import com.nordea.countries.exceptions.CountriesServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nordea.countries.Constants.*;

@CrossOrigin
@RestController
@RequestMapping(value = BASE_URL)
public class CountriesController {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CountriesController.class);

    @GetMapping(value = "/")
    public ResponseEntity<Countries> getAllCountriesData(){
        Countries countries;
        ArrayList<LinkedHashMap<String,Object>> countriesResponseDataList;
        try {
            countriesResponseDataList =  restTemplate.getForObject(REST_COUNTRIES_BASE_URL+"/all?"+FIELDS_STRING+"="+FIELDS,ArrayList.class);
            if(countriesResponseDataList != null) {
                List<Country> countryList = countriesResponseDataList.stream().map(linkedHashMap -> populateCountry(linkedHashMap)).collect(Collectors.toList());
                countries = new Countries();
                countries.setCountries(countryList);
                return new ResponseEntity<>(countries, HttpStatus.OK);
            }else{
                throw this.generateCountryNotFoundException();
            }
        }catch (HttpClientErrorException e){
            throw this.generateCountryNotFoundException();
        } catch (Exception e){
            throw this.generateCountiesServiceException();
        }

    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<CountryDetail>  getCountryData(@PathVariable("name") String countryName)  {
        logger.debug("getCountryData: country {}", countryName);
        CountryDetail countryDetail;
        ArrayList<LinkedHashMap<String, Object>> countriesResponseDataList;
        try {
            countriesResponseDataList = restTemplate.getForObject(REST_COUNTRIES_BASE_URL + "/name/" + countryName + "?fullText=true", ArrayList.class);
            if (countriesResponseDataList != null) {
                Optional<CountryDetail> countryDetailOptional = countriesResponseDataList.stream().findFirst().map(linkedHashMap -> populateCountryDetail(linkedHashMap));
                if (countryDetailOptional.isPresent()) {
                    countryDetail = countryDetailOptional.get();
                    return new ResponseEntity<>(countryDetail, HttpStatus.OK);
                } else {
                    throw this.generateCountryNotFoundException();
                }
            } else {
                throw this.generateCountryNotFoundException();
            }
        } catch (HttpClientErrorException e) {
            throw this.generateCountryNotFoundException();
        } catch (Exception e) {
            throw this.generateCountiesServiceException();
        }
    }

    private CountriesServiceException generateCountiesServiceException(){
        return new CountriesServiceException("Error fetching data from external countries service");
    }


    private CountryNotFoundException generateCountryNotFoundException(){
        return new CountryNotFoundException("Country data not found");
    }


    private Country populateCountry(LinkedHashMap<String,Object> responseMap){
        String name = (String) responseMap.get("name");
        String alpha2Code = (String) responseMap.get("alpha2Code");
        return new Country(name,alpha2Code);
    }
    private CountryDetail populateCountryDetail(LinkedHashMap<String,Object> responseMap) {
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

}
