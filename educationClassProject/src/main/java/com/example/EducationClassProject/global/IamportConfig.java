package com.example.EducationClassProject.global;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IamportConfig {

    @Value("${iamport.api_Key}")
    private String apiKey;

    @Value("${iamport.api_Secret}")
    private String apiSecret;

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(apiKey, apiSecret);
    }
}
