package codes.fdk.sample.contract.pside.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.web.reactive.function.client.WebClientResponseException.BadRequest;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest(webEnvironment = NONE)
@AutoConfigureStubRunner(stubsMode = LOCAL, ids = "codes.fdk.sample.contract.pside:producer:+:stubs")
class BookApiClientTest {

    @Autowired private BookApiClient client;

    @Test
    void createBookWithValidInputShouldReturnResponseWithIsbnAndInputValues() {
        StepVerifier.create(client.createBook(new PostBookRequest("title", "description")))
                    .assertNext(bookResponse -> assertAll(
                            () -> assertThat(bookResponse.isbn).isNotNull(),
                            () -> assertThat(bookResponse.title).isEqualTo("title"),
                            () -> assertThat(bookResponse.description).isEqualTo("description")
                    ))
                    .verifyComplete();
    }

    @Test
    void createBookWithBlankTitleShouldReturnBadRequest() {
        StepVerifier.create(client.createBook(new PostBookRequest("  ", "description")))
                    .verifyError(BadRequest.class);
    }

    @Test
    void createBookWithNullTitleShouldReturnBadRequest() {
        StepVerifier.create(client.createBook(new PostBookRequest(null, "description")))
                    .verifyError(BadRequest.class);
    }

}