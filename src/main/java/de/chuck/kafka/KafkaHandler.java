package de.chuck.kafka;

import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import de.chuck.ais.AisMessage;
import io.smallrye.reactive.messaging.kafka.Record;

@ApplicationScoped
public class KafkaHandler {

    @Inject
    @Channel("data-output")
    Emitter<Record<String, AisMessage>> emitter;

    @Inject
    DataProvider provider;

    @Inject
    DataSplitter<AisMessage> splitter;

    @Inject
    KeyProvider keyProvider;

    @Incoming("event-input")
    public void handleEvent(Record<String,String> event) throws IOException {
        System.out.println("Got record " + event.key());
        try (InputStream input = provider.open(event.key())) {
            splitter.split(input, data -> emitter.send(Record.of(keyProvider.getKey(data), data)));
        }
    }

}
