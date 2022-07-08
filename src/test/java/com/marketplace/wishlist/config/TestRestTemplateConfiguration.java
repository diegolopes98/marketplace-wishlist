package com.marketplace.wishlist.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class TestRestTemplateConfiguration {

    private final String WISHLIST_URI = "http://localhost";
    private final String WISHLIST_FULL_URL;

    @Autowired
    public TestRestTemplateConfiguration(
            @Value("${server.port}") String VCLOUD_AUTH_MANAGER_TEST_PORT) {
        this.WISHLIST_FULL_URL = String.format(
                "%s:%s",
                WISHLIST_URI,
                VCLOUD_AUTH_MANAGER_TEST_PORT
        );
    }

    @Bean("rest-template")
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .build();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(WISHLIST_FULL_URL));
        return restTemplate;
    }
}
