package com.nordea.countries.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CountryExceptionHandler {

    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> getCountryNotFoundException(CountryNotFoundException countryNotFoundException) {
        ErrorMessage responseMessage = new ErrorMessage(countryNotFoundException.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CountriesServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> getInternalServerException(CountriesServiceException internalServerErrorException) {
        ErrorMessage responseMessage = new ErrorMessage(internalServerErrorException.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
