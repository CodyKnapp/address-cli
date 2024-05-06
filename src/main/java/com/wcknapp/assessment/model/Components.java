package com.wcknapp.assessment.model;

public class Components {
    private String city;
    private String zip;
    private String zipPlus4;

    public Components() {
        this.city = null;
        this.zip = null;
        this.zipPlus4 = null;
    }

    public Components(String city, String zip, String zipPlus4) {
        this.city = city;
        this.zip = zip;
        this.zipPlus4 = zipPlus4;
    }

    public void setCity_name(String city) {
        this.city = city;
    }

    public void setZipcode(String zip) {
        this.zip = zip;
    }

    public void setPlus4_code(String zipPlus4) {
        this.zipPlus4 = zipPlus4;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getZipPlus4() {
        return zipPlus4;
    }
}
