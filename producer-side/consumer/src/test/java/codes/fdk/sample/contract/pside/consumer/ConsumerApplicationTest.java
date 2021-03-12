package codes.fdk.sample.contract.pside.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest(webEnvironment = NONE)
@AutoConfigureStubRunner(stubsMode = LOCAL, ids = "codes.fdk.sample.contract.pside:producer:+:stubs")
class ConsumerApplicationTest {

    @Autowired private ApiClient client;

    @Test
    void allShouldReturnListOfObjectsFromApi() {
        StepVerifier.create(client.all())
                    .assertNext(first -> assertThat(first.getBar()).isEqualTo("bar"))
                    .assertNext(second -> assertThat(second.getBar()).isEqualTo("baz"))
                    .verifyComplete();
    }

}