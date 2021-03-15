package codes.fdk.sample.contract.pside.producer;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private static final Map<UUID, BookEntity> STORE = new HashMap<>();

    @Override
    public Mono<BookEntity> save(BookEntity entity) {
        return Mono.fromCallable(() -> {
            final BookEntity entityWithIsbn = setIsbnIfNotPresent(entity);
            return STORE.compute(entityWithIsbn.isbn, (k, v) -> entityWithIsbn);
        });
    }

    @Override
    public Mono<BookEntity> findByIsbn(UUID isbn) {
        return Mono.justOrEmpty(STORE.get(isbn));
    }

    private BookEntity setIsbnIfNotPresent(BookEntity entity) {
        if (Objects.isNull(entity.isbn)) {
            final UUID isbn = UUID.randomUUID();
            return new BookEntity(isbn, entity.title, entity.description);
        }

        return entity;
    }

}
