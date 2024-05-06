package com.wcknapp.assessment.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressResponseModelTest {
    @Test
    public void getFullAddressReturnsTheCorrectlyFormattedAddress() {
        AddressResponseModel addressResponseModel = new AddressResponseModel("123 Main St", new Components("Springfield", "12345", "6789"));
        assertEquals("123 Main St, Springfield, 12345-6789", addressResponseModel.getFullAddress());
    }
}