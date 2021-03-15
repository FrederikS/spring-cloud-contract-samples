package codes.fdk.sample.contract.pside.producer;

import javax.validation.constraints.NotBlank;

public class PostBookRequest {

    @NotBlank public final String title;
    public final String description;

    public PostBookRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
