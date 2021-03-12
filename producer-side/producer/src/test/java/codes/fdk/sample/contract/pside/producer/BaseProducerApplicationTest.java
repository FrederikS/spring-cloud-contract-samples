package codes.fdk.sample.contract.pside.producer;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BaseProducerApplicationTest {

    @LocalServerPort int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + this.port;
    }

}