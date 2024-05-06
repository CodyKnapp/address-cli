package com.wcknapp.assessment;

import com.wcknapp.assessment.client.AddressVerificationClient;
import com.wcknapp.assessment.model.AddressModel;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AddressVerificationClientIntegrationTest {
    @Test
    public void VerifyAddressShouldReturnEmptyOptionalWhenAddressIsInvalid() {
        var subject = new AddressVerificationClient();
        var invalidAddress = new AddressModel("1 Empora St", "Title", "11111");

        var result = subject.verifyAddress(invalidAddress);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void VerifyAddressShouldReturnCorrectAddressWhenAddressIsValid() {
        var subject = new AddressVerificationClient();
        var validAddress = new AddressModel("800 N High St", "Columbus", "43215");

        var result = subject.verifyAddress(validAddress);

        assertNotEquals(Optional.empty(), result);
        var response = result.get();
        assertEquals("800 N High St", response.getStreet());
        assertEquals("Columbus", response.getComponents().getCity());
        assertEquals("43215", response.getComponents().getZip());
        assertEquals("1430", response.getComponents().getZipPlus4());
    }
}