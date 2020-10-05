package com.nordea.countries.controllers;




import com.nordea.countries.dto.Countries;
import com.nordea.countries.dto.Country;
import com.nordea.countries.dto.CountryDetail;
import com.nordea.countries.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final RestTemplate restTemplate;

    private final CountryService countryService;

    private static final Logger logger = LoggerFactory.getLogger(CountriesController.class);

    public CountriesController(RestTemplate restTemplate, CountryService countryService) {
        this.restTemplate = restTemplate;
        this.countryService = countryService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<Countries> getAllCountriesData(){

        ArrayList<LinkedHashMap<String,String>> countriesResponseDataList;
        try {
            countriesResponseDataList =  restTemplate.getForObject(REST_COUNTRIES_BASE_URL+"/all?"+FIELDS_STRING+"="+FIELDS,ArrayList.class);
            if(countriesResponseDataList != null) {
                List<Country> countryList = countriesResponseDataList.stream().map(linkedHashMap -> countryService.populateCountry(linkedHashMap)).collect(Collectors.toList());
                Countries countries = countryService.populateCountries(countryList);
                return new ResponseEntity<>(countries, HttpStatus.OK);
            }else{
                throw countryService.generateCountryNotFoundException();
            }
        }catch (HttpClientErrorException e){
            logger.error(e.getMessage());
            throw countryService.generateCountryNotFoundException();
        } catch (Exception e){
            logger.error(e.getMessage());
            throw countryService.generateCountiesServiceException();
        }

    }

    @GetMapping("/{name}")
    public ResponseEntity<CountryDetail>  getCountryData(@PathVariable("name") String countryName)  {
        logger.debug("getCountryData: country {}", countryName);
        CountryDetail countryDetail;
        ArrayList<LinkedHashMap<String, Object>> countriesResponseDataList;
        try {
            countriesResponseDataList = restTemplate.getForObject(REST_COUNTRIES_BASE_URL + "/name/" + countryName + "?fullText=true", ArrayList.class);
            if (countriesResponseDataList != null) {
                Optional<CountryDetail> countryDetailOptional = countriesResponseDataList.stream().findFirst().map(linkedHashMap -> countryService.populateCountryDetail(linkedHashMap));
                if (countryDetailOptional.isPresent()) {
                    countryDetail = countryDetailOptional.get();
                    return new ResponseEntity<>(countryDetail, HttpStatus.OK);
                } else {
                    throw countryService.generateCountryNotFoundException();
                }
            } else {
                throw countryService.generateCountryNotFoundException();
            }
        } catch (HttpClientErrorException e) {
            logger.error(e.getMessage());
            throw countryService.generateCountryNotFoundException();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw countryService.generateCountiesServiceException();
        }
    }


}
