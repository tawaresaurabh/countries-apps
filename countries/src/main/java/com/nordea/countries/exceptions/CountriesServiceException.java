package com.nordea.countries.exceptions;

public class CountriesServiceException extends RuntimeException{

    public CountriesServiceException(String message) {
        super(message);
    }
}
