package codes.fdk.sample.contract.pside.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class BookApiClient {

    private final WebClient client;

    @Autowired
    public BookApiClient(WebClient client) {
        this.client = client;
    }

    public Mono<BookResponse> createBook(PostBookRequest request) {
        return client.post().uri("/books")
                     .contentType(APPLICATION_JSON)
                     .bodyValue(request)
                     .retrieve()
                     .bodyToMono(BookResponse.class);
    }

}
