package com.wcknapp.assessment.client;

import com.wcknapp.assessment.model.AddressModel;
import com.wcknapp.assessment.model.AddressResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
@PropertySource("classpath:config.properties")
public class AddressVerificationClient {
    private final RestClient client;

    @Value("${smarty.auth.id}")
    private String authId;
    @Value("${smarty.auth.token}")
    private String authToken;
    @Value("${smarty.auth.license}")
    private String license;

    public AddressVerificationClient() {
        this.client = RestClient.create("https://us-street.api.smarty.com/");
    }

    public Optional<AddressResponseModel> verifyAddress(AddressModel address) {
        var endpoint = "street-address?auth-id={0}&auth-token={1}&license={2}&street={3}&zipcode={4}";
        var response = client
                .get()
                .uri(endpoint, authId, authToken, license, address.street(), address.zip())
                .retrieve()
                .body(AddressResponseModel[].class);

        return Optional.ofNullable(response).map(responses -> responses.length > 0 ? responses[0] : null);
    }
}
