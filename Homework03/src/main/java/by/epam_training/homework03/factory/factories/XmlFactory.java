package by.epam_training.homework03.factory.factories;

import by.epam_training.homework03.factory.FileAbstractFactory;
import by.epam_training.homework03.parser.FileParser;
import by.epam_training.homework03.parser.impl.XmlParser;
import by.epam_training.homework03.reader.FileDataReader;
import by.epam_training.homework03.reader.impl.XmlFileReader;

public class XmlFactory implements FileAbstractFactory {
    @Override
    public FileParser getFileParser() {
        return new XmlParser();
    }

    @Override
    public FileDataReader getFileDataReader() {
        return new XmlFileReader();
    }
}
