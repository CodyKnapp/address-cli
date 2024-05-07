package com.wcknapp.assessment.processor;

import com.wcknapp.assessment.client.AddressVerificationClient;
import com.wcknapp.assessment.model.AddressModel;
import com.wcknapp.assessment.model.AddressResponseModel;
import com.wcknapp.assessment.model.Components;
import com.wcknapp.assessment.processor.FileProcessor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class FileProcessorTest {

    @MockBean
    private AddressVerificationClient mockAddressVerificationClient;

    @Autowired
    private FileProcessor subject;

    @Test
    public void processFileCorrectsEachAddressInAWellFormedFile() {
        String fileName = "src/test/resources/testFile.csv";
        Mockito.when(mockAddressVerificationClient.verifyAddress(new AddressModel("143 e Maine Street", "Columbus", "43215")))
                .thenReturn(Optional.of(new AddressResponseModel("143 E Main St", new Components("Columbus", "43215", "5370"))));
        Mockito.when(mockAddressVerificationClient.verifyAddress(new AddressModel("1 Empora St", "Title", "11111")))
                .thenReturn(Optional.empty());

        var results = subject.processFile(fileName);

        assertEquals(2, results.size());
        assertEquals("143 e Maine Street, Columbus, 43215 -> 143 E Main St, Columbus, 43215-5370", results.get(0));
        assertEquals("1 Empora St, Title, 11111 -> Invalid Address", results.get(1));
    }

    @Test
    public void processFileReturnsEmptyListWhenFileIsNotPresent() {
        String fileName = "src/test/resources/nonExistentFile.csv";

        var results = subject.processFile(fileName);

        assertEquals(0, results.size());
    }

    @Test
    public void processFileReturnsEmptyListWhenFileIsOnlyHeaderRow() {
        String fileName = "src/test/resources/headerOnlyFile.csv";

        var results = subject.processFile(fileName);

        assertEquals(0, results.size());
    }
}