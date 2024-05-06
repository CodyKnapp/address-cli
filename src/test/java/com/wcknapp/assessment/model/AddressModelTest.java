package com.wcknapp.assessment.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressModelTest {

    @Test
    public void fullAddressReturnsTheCorrectlyFormattedAddress() {
        AddressModel addressModel = new AddressModel("123 Main St", "Springfield", "12345");
        assertEquals("123 Main St, Springfield, 12345", addressModel.fullAddress());
    }
}