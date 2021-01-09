package codes.fdk.sample.contract.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest
@AutoConfigureStubRunner(stubsMode = LOCAL, ids = "codes.fdk.sample.contract:producer:+:stubs:8081")
class ConsumerApplicationTest {

    @Autowired private ApiClient client;

    @Test
    void name() {
        StepVerifier.create(client.all())
                    .assertNext(first -> assertThat(first.getBar()).isEqualTo("bar"))
                    .assertNext(second -> assertThat(second.getBar()).isEqualTo("baz"))
                    .verifyComplete();
    }

}