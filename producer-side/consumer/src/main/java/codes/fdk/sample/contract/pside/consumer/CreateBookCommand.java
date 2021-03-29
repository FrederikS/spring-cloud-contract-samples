package codes.fdk.sample.contract.pside.consumer;

public class CreateBookCommand {

    public final String title;
    public final String description;

    public CreateBookCommand(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
