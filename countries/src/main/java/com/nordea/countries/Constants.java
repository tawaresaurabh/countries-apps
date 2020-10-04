package com.nordea.countries;

import java.util.StringJoiner;

public class Constants {
    public static final String REST_COUNTRIES_BASE_URL = "https://restcountries.eu/rest/v2";
    public static final String FIELDS_STRING = "fields";
    public static final String NAME_FIELD = "name";
    public static final String ALPHA_2_CODE_FIELD = "alpha2Code";
    public static final String FIELDS  = new StringJoiner(";").add(NAME_FIELD).add(ALPHA_2_CODE_FIELD).toString();
    public static final String BASE_URL = "/countries";


}
