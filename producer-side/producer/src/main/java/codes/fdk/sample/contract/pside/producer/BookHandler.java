package codes.fdk.sample.contract.pside.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@Component
class BookHandler {

    private final Validator validator;
    private final BookRepository bookRepository;

    @Autowired
    BookHandler(BookRepository bookRepository, Validator validator) {
        this.bookRepository = bookRepository;
        this.validator = validator;
    }

    @NonNull
    Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(PostBookRequest.class)
                      .switchIfEmpty(Mono.error(new ResponseStatusException(BAD_REQUEST, "No input provided.")))
                      .map(this::validate)
                      .map(bookRequest -> new BookEntity(null, bookRequest.title, bookRequest.description))
                      .flatMap(bookRepository::save)
                      .map(BookHandler::toDto)
                      .flatMap(book -> ServerResponse.created(fromPath("/books/{isbn}").build(book.isbn))
                                                     .bodyValue(book));
    }

    @NonNull
    Mono<ServerResponse> findByIsbn(ServerRequest request) {
        String isbnFromPath = request.pathVariable("isbn");
        final UUID isbn = tryToParseUUID(isbnFromPath).orElseThrow(() -> new ResponseStatusException(
                BAD_REQUEST,
                "Given isbn is invalid."
        ));

        return bookRepository.findByIsbn(isbn)
                             .map(BookHandler::toDto)
                             .flatMap(bookResponse -> ServerResponse.ok().bodyValue(bookResponse))
                             .switchIfEmpty(ServerResponse.notFound().build());
    }

    private static Optional<UUID> tryToParseUUID(String isbn) {
        try {
            return Optional.of(UUID.fromString(isbn));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private <T> T validate(T input) {
        final Set<ConstraintViolation<T>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            final String message = violations.stream()
                                             .map(v -> v.getPropertyPath() + " " + v.getMessage())
                                             .collect(Collectors.joining(", "));

            throw new ResponseStatusException(BAD_REQUEST, message);
        }

        return input;
    }

    private static BookResponse toDto(BookEntity book) {
        return new BookResponse(book.isbn, book.title, book.description);
    }

}
