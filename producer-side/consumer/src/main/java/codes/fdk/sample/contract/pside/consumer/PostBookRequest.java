package codes.fdk.sample.contract.pside.consumer;

public class PostBookRequest {

    public final String title;
    public final String description;

    public PostBookRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
