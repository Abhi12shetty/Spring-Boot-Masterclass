package com.traning.ecommerce.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//Day 29: Calling Third-Party APIs (RestTemplate)
@Service
public class ExternalApiService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalApiService.class);

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object fetchExternalProduct() {
        // The URL of the third-party API we want to call
        String apiUrl = "https://fakestoreapi.com/products/1";

        logger.info("Calling external API: {}", apiUrl);

        // We tell RestTemplate to do a GET request and turn the JSON into a generic Java Object
        Object response = restTemplate.getForObject(apiUrl, Object.class);

        logger.info("Successfully fetched data from external API!");

        return response;
    }
}