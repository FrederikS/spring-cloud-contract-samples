package codes.fdk.sample.contract.pside.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.validation.Validator;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class);
    }

    @Bean
    RouterFunction<ServerResponse> routes(BookHandler bookHandler) {
        return route()
                .POST("/books", accept(APPLICATION_JSON), bookHandler::create)
                .GET("/books/{isbn}", accept(APPLICATION_JSON), bookHandler::findByIsbn)
                .build();
    }

    @Bean
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}
