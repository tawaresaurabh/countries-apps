package com.nordea.countries.controllers;

import com.nordea.countries.dto.Countries;
import com.nordea.countries.dto.CountryDetail;
import com.nordea.countries.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static com.nordea.countries.Constants.*;
import static com.nordea.countries.Constants.FIELDS;

@RestController
@RequestMapping(value = BASE_URL_REACTIVE)
public class ReactiveCountriesController {

    private final WebClient webClient;

    private final CountryService countryService;

    private static final Logger logger = LoggerFactory.getLogger(CountriesController.class);

    public ReactiveCountriesController(WebClient webClient, CountryService countryService) {
        this.webClient = webClient;
        this.countryService = countryService;
    }


    @GetMapping("/")
    public Mono<Countries> listCountries() {
        return webClient.get()
                .uri(REST_COUNTRIES_BASE_URL + "/all?" + FIELDS_STRING + "=" + FIELDS)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(countryService.generateCountryNotFoundException()))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(countryService.generateCountiesServiceException()))
                .bodyToMono(new ParameterizedTypeReference<ArrayList<LinkedHashMap<String, String>>>() {
                })
                .map(linkedHashMaps -> countryService.populateCountries(linkedHashMaps.stream().map(linkedHashMap -> countryService.populateCountry(linkedHashMap)).collect(Collectors.toList())));


    }

    @GetMapping("/{name}")
    public Mono<CountryDetail> getCountry(@PathVariable("name") String countryName) {
        return webClient.get()
                .uri(REST_COUNTRIES_BASE_URL + "/name/" + countryName + "?fullText=true")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(countryService.generateCountryNotFoundException()))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(countryService.generateCountiesServiceException()))
                .bodyToMono(new ParameterizedTypeReference<ArrayList<LinkedHashMap<String, Object>>>() {
                })
                .map(linkedHashMaps -> countryService.populateCountryDetail(linkedHashMaps.stream().findFirst().get()));

    }
}
