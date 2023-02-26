package de.chuck.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

public interface DataSplitter<T> {
    
    public void split(InputStream inputStream, Consumer<T> consumer) throws IOException;

}
