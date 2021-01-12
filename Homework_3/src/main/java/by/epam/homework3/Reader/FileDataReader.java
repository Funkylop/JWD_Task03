package by.epam.homework3.Reader;

import java.io.IOException;
import java.util.List;

public interface FileDataReader {
    List<String> read(String s) throws IOException;
}
