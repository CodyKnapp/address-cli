package com.wcknapp.assessment.client;

import com.wcknapp.assessment.ConfigReader;
import com.wcknapp.assessment.model.AddressModel;
import com.wcknapp.assessment.model.AddressResponseModel;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class AddressVerificationClient {
    private final RestClient client;
    private final String authId;
    private final String authToken;
    private static final String LICENSE = "us-core-cloud";

    public AddressVerificationClient() {
        this.client = RestClient.create("https://us-street.api.smarty.com/");
        var properties = ConfigReader.read("config.properties");
        this.authId = properties.getProperty("AUTH_ID");
        this.authToken = properties.getProperty("AUTH_TOKEN");
    }

    public Optional<AddressResponseModel> verifyAddress(AddressModel address) {
        var endpoint = "street-address?auth-id={0}&auth-token={1}&license={2}&street={3}&zipcode={4}";
        var response = client
                .get()
                .uri(endpoint, authId, authToken, LICENSE, address.street(), address.zip())
                .retrieve()
                .body(AddressResponseModel[].class);

        return Optional.ofNullable(response).map(responses -> responses.length > 0 ? responses[0] : null);
    }
}
