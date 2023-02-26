package de.chuck.kafka;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@Default
@ApplicationScoped
public class LocalDataProvider implements DataProvider {

    @Override
    public InputStream open(String location) throws IOException {
        return new FileInputStream(location);
    }
    
}
