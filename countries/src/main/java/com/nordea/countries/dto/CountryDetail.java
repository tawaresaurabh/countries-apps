package com.nordea.countries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryDetail extends Country {
    private String capital;
    private Integer population;

    @JsonProperty("flag_file_url")
    private String flagFileUrl;

    public CountryDetail(String name, String countryCode) {
        super(name, countryCode);
    }

    public CountryDetail(String name, String countryCode, String capital, Integer population, String flagFileUrl) {
        super(name, countryCode);
        this.capital = capital;
        this.population = population;
        this.flagFileUrl = flagFileUrl;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getFlagFileUrl() {
        return flagFileUrl;
    }

    public void setFlagFileUrl(String flagFileUrl) {
        this.flagFileUrl = flagFileUrl;
    }
}
