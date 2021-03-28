package codes.fdk.sample.contract.pside.producer;

import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BaseProducerTest {

    @Autowired
    private ApplicationContext context;

    @BeforeEach
    void setUp() {
        RestAssuredWebTestClient.applicationContextSetup(context);
    }

}