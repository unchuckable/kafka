package org.acme;

import java.util.HashMap;
import java.util.Map;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.providers.connectors.InMemoryConnector;

public class KafkaTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        Map<String, String> env = new HashMap<>();
        env.putAll(InMemoryConnector.switchIncomingChannelsToInMemory("event-input"));
        env.putAll(InMemoryConnector.switchOutgoingChannelsToInMemory("data-output"));
        return env;
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();
    }
    
}
