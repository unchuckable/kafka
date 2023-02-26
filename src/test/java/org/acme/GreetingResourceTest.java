package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.enterprise.inject.Any;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;

import com.google.inject.Inject;

import de.chuck.ais.AisMessage;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.kafka.Record;
import io.smallrye.reactive.messaging.providers.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.providers.connectors.InMemorySink;
import io.smallrye.reactive.messaging.providers.connectors.InMemorySource;

@QuarkusTest
@QuarkusTestResource(KafkaTestLifecycleManager.class)
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

    @Inject
    @Any
    InMemoryConnector connector;

    @Test
    public void testKafkaHandling() {
        InMemorySource<Record<String,String>> eventInput = connector.source("event-input");
        InMemorySink<Record<String,AisMessage>> aisOutput = connector.sink("data-output");
        
        eventInput.send(Record.of("Hans", "Peter"));

        Awaitility.await().until(aisOutput::received, list -> list.size() > 0);

        System.out.println(aisOutput.received().get(0).getPayload().value());
        
    }

}