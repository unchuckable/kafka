package de.chuck.kafka;

import java.io.IOException;
import java.io.InputStream;

public interface DataProvider {
    
    public InputStream open(String location) throws IOException;

}
