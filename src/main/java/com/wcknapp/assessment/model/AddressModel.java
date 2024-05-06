package com.wcknapp.assessment.model;

public record AddressModel(String street, String city, String zip) {
    public String fullAddress() {
        return street + ", " + city + ", " + zip;
    }
}
