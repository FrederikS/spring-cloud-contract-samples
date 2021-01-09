package codes.fdk.sample.contract.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

@SpringBootApplication
@EnableConfigurationProperties(ApiClientConfigurationProperties.class)
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }

    @Bean
    RouterFunction<ServerResponse> routes(ApiClient client) {
        return route()
                .GET("/foo", accept(APPLICATION_JSON), request -> {
                    return ServerResponse.ok()
                                         .contentType(APPLICATION_JSON)
                                         .body(client.all(), Foo.class);
                })
                .build();
    }

}
