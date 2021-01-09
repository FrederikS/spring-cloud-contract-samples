package codes.fdk.sample.contract.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiClientConfiguration {

    @Bean
    public WebClient client(WebClient.Builder webClientBuilder, ApiClientConfigurationProperties clientProperties) {
        return webClientBuilder.baseUrl(clientProperties.getBaseUrl())
                               .build();
    }

}
