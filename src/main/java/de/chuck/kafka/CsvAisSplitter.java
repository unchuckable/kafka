package de.chuck.kafka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.function.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

import de.chuck.ais.AisMessage;

@Default
@ApplicationScoped
public class CsvAisSplitter implements DataSplitter<AisMessage> {

    @Override
    public void split(InputStream inputStream, Consumer<AisMessage> consumer) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader.lines().map(this::convertStringToAisMessage).forEach(consumer::accept);
        }
    }

    private AisMessage convertStringToAisMessage(String line) {
        System.out.println(line);
        return AisMessage.newBuilder()
                    .setTimestamp(Instant.now())
                    .setMmsi(line)
                    .build();
    }

    
}
