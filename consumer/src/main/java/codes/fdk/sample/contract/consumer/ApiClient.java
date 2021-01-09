package codes.fdk.sample.contract.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class ApiClient {

    private final WebClient client;

    @Autowired
    public ApiClient(WebClient client) {
        this.client = client;
    }

    public Flux<Foo> all() {
        return client.get().uri("/foo")
                     .accept(MediaType.APPLICATION_JSON)
                     .retrieve()
                     .bodyToFlux(Foo.class);
    }

}
