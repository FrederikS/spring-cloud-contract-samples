package codes.fdk.sample.contract.pside.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class);
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route()
                .GET("/foo", accept(APPLICATION_JSON), request -> {
                    return ServerResponse.ok()
                                         .contentType(APPLICATION_JSON)
                                         .bodyValue(List.of(new Foo("bar"), new Foo("baz")));
                })
                .build();
    }

}
