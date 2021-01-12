package by.epam.homework3.factory.impl;

import by.epam.homework3.Parser.FileDataParser;
import by.epam.homework3.Parser.impl.XmlParser;
import by.epam.homework3.Reader.FileDataReader;
import by.epam.homework3.Reader.impl.XmlFileReader;
import by.epam.homework3.factory.FileFactory;

public class XmlFileFactory implements FileFactory {
    @Override
    public FileDataParser getParser() {
        return new XmlParser();
    }

    @Override
    public FileDataReader getFileReader() {
        return new XmlFileReader();
    }
}
