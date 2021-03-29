package codes.fdk.sample.contract.pside.consumer;

import java.util.UUID;

public class Book {

    public final UUID isbn;
    public final String title;
    public final String description;

    public Book(UUID isbn, String title, String description) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
    }

}
