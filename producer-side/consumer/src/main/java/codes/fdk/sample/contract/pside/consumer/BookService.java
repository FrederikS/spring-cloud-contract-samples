package codes.fdk.sample.contract.pside.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class BookService {

    private final BookApiClient bookApiClient;

    @Autowired
    public BookService(BookApiClient bookApiClient) {
        this.bookApiClient = bookApiClient;
    }

    public Mono<Book> addBook(CreateBookCommand command) {
        return bookApiClient.postBook(toRequest(command))
                            .map(BookService::toDomainModel);
    }

    public Mono<Book> findBookByIsbn(UUID isbn) {
        return bookApiClient.getBookByIsbn(isbn)
                            .onErrorResume(e -> e instanceof NotFound ? Mono.empty() : Mono.error(e))
                            .map(BookService::toDomainModel);
    }

    private static PostBookRequest toRequest(CreateBookCommand command) {
        return new PostBookRequest(command.title, command.description);
    }

    private static Book toDomainModel(BookResponse response) {
        return new Book(response.isbn, response.title, response.description);
    }

}
