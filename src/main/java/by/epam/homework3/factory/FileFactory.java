package by.epam.homework3.factory;

import by.epam.homework3.Parser.FileDataParser;
import by.epam.homework3.Reader.FileDataReader;

public interface FileFactory {
    FileDataParser getParser();
    FileDataReader getFileReader();
}
