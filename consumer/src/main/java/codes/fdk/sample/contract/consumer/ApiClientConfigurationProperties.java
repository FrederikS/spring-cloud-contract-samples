package codes.fdk.sample.contract.consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("client.api")
public class ApiClientConfigurationProperties {

    private final String baseUrl;

    public ApiClientConfigurationProperties(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

}
