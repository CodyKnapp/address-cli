package com.wcknapp.assessment.model;

public class AddressResponseModel {
    private String street;
    private Components components;

    public AddressResponseModel() {
        this.street = null;
        this.components = null;
    }

    public AddressResponseModel(String delivery_line_1, Components components) {
        this.street = delivery_line_1;
        this.components = components;
    }

    public void setDelivery_line_1(String street) {
        this.street = street;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public String getStreet() {
        return street;
    }

    public Components getComponents() {
        return components;
    }

    public String getFullAddress() {
        return street + ", " + components.getCity() + ", " + components.getZip() + "-" + components.getZipPlus4();
    }
}
