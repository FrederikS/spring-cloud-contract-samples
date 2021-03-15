package codes.fdk.sample.contract.pside.producer;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BookRepository {

    Mono<BookEntity> save(BookEntity entity);
    Mono<BookEntity> findByIsbn(UUID isbn);

}
