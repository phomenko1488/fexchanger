package iam.anonymous.exchange.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateBean {
    private final RestTemplate restTemplate = new RestTemplate();

    @Bean
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
