package codes.fdk.sample.contract.pside.consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static codes.fdk.sample.contract.pside.consumer.BookApiClientConfiguration.ApiClientProperties;

@Configuration
@EnableConfigurationProperties(ApiClientProperties.class)
public class BookApiClientConfiguration {

    @Bean
    public WebClient client(WebClient.Builder webClientBuilder, ApiClientProperties config) {
        return webClientBuilder.baseUrl(config.getBaseUrl())
                               .build();
    }

    @ConstructorBinding
    @ConfigurationProperties("web.client")
    public static class ApiClientProperties {

        private final String baseUrl;

        public ApiClientProperties(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

    }
}
