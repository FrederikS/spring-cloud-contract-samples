package codes.fdk.sample.contract.pside.producer;

import java.util.UUID;

public class BookResponse {

    public final UUID isbn;
    public final String title;
    public final String description;

    public BookResponse(UUID isbn, String title, String description) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
    }

}
