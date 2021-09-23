package com.example.geocoder.entitys;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Hit {
    private Points points;
    private Double [] extent;
    private String name;
    private String country;
    private String countrycode;
    private String city;
    private String state;
    private String street;
    private String postcode;
    private BigInteger osm_id;
    private String osm_type;
    private String housenumber;
    private String osm_key;
    private String osm_value;
    private String house_number;

}
