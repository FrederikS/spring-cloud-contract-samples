package codes.fdk.sample.contract.pside.producer;

import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BaseProducerTest {

    @Autowired private ApplicationContext context;
    @Autowired private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        initMockData();
        RestAssuredWebTestClient.applicationContextSetup(context);
    }

    private void initMockData() {
        bookRepository.save(new BookEntity(
                UUID.fromString("29c114c1-4c26-4180-bd4d-6973767b3c61"),
                "The Fellowship of the Ring",
                "Lord of the rings, first part."
        )).block();
    }

}
