package com.wcknapp.assessment;

import com.wcknapp.assessment.client.AddressVerificationClient;
import com.wcknapp.assessment.model.AddressModel;
import com.wcknapp.assessment.model.AddressResponseModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileProcessor {
    private final AddressVerificationClient addressVerificationClient;
    private final Logger logger = LoggerFactory.getLogger(FileProcessor.class);

    public FileProcessor(AddressVerificationClient addressVerificationClient) {
        this.addressVerificationClient = addressVerificationClient;
    }

    public List<String> processFile(String fileName) {
        logger.info("Processing file: " + fileName);

        try (Reader reader = new FileReader(fileName)) {
            var format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setHeader("street", "city", "zip")
                    .setSkipHeaderRecord(true)
                    .setIgnoreSurroundingSpaces(true)
                    .build();

            CSVParser parser = new CSVParser(reader, format);

            return parser.stream()
                    .map(record -> new AddressModel(record.get("street"), record.get("city"), record.get("zip")))
                    .map(this::correctAddress)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error processing file: " + fileName, e);
        }

        return List.of();
    }

    private String correctAddress(AddressModel address) {
        var response = addressVerificationClient.verifyAddress(address);
        var correctedAddress = response.map(AddressResponseModel::getFullAddress).orElse("Invalid Address");
        return address.fullAddress() + " -> " + correctedAddress;
    }
}