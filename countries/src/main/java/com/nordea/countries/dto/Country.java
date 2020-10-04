package com.nordea.countries.dto;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Country {


    private String name;
    @JsonProperty("country_code")
    private String countryCode;


    public Country() {
    }

    public Country(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }



}
