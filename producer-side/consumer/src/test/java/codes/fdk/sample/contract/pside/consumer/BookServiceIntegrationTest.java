package codes.fdk.sample.contract.pside.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest(webEnvironment = NONE)
@AutoConfigureStubRunner(stubsMode = LOCAL, ids = "codes.fdk.sample.contract.pside:producer:+:stubs")
class BookServiceIntegrationTest {

    @Autowired private BookService bookService;

    @Test
    void addBookWithValidInputShouldReturnBookWithIsbnAssignedAndInputValues() {

        StepVerifier.create(bookService.addBook(new CreateBookCommand("title", "description")))
                    .assertNext(book -> assertAll(
                            () -> assertThat(book.isbn).isNotNull(),
                            () -> assertThat(book.title).isEqualTo("title"),
                            () -> assertThat(book.description).isEqualTo("description")
                    ))
                    .verifyComplete();
    }

    @Test
    void addBookWithBlankTitleShouldThrowAnError() {
        StepVerifier.create(bookService.addBook(new CreateBookCommand("  ", "description")))
                    .verifyError();
    }

    @Test
    void addBookWithoutTitleShouldThrowAnError() {
        StepVerifier.create(bookService.addBook(new CreateBookCommand(null, "description")))
                    .verifyError();
    }

    @Test
    void findBookByIsbnShouldBeEmptyForNonExistentISBN() {
        StepVerifier.create(bookService.findBookByIsbn(UUID.randomUUID()))
                    .verifyComplete();
    }

    @Test
    void findBookByIsbnShouldReturnBookForExistentISBN() {
        StepVerifier.create(bookService.addBook(new CreateBookCommand("title", "description"))
                                       .flatMap(book -> bookService.findBookByIsbn(book.isbn)))
                    .expectNextCount(1)
                    .verifyComplete();
    }

}
