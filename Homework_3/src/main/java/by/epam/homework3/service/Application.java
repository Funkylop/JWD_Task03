package by.epam.homework3.service;

import by.epam.homework3.Parser.FileDataParser;
import by.epam.homework3.Reader.FileDataReader;
import by.epam.homework3.factory.FileFactory;
import by.epam.homework3.factory.impl.XmlFileFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public static final String FILE_EXTENSION = "\\..\\w+.";
    private FileDataReader fileDataReader;
    private FileDataParser fileParser;

    public Application(FileFactory factory) {
        fileDataReader = factory.getFileReader();
        fileParser = factory.getParser();
    }

    public FileDataReader getFileDataReader() {
        return fileDataReader;
    }

    public void setFileDataReader(FileDataReader fileDataReader) {
        this.fileDataReader = fileDataReader;
    }

    public FileDataParser getFileDataParser() {
        return fileParser;
    }

    public void setFileParser(FileDataParser fileParser) {
        this.fileParser = fileParser;
    }


    public static Application configurationFile(String path) {
        FileFactory factory;
        Application fileApplication = null;

        Pattern typeOfFilePattern = Pattern.compile(FILE_EXTENSION);
        Matcher typeOfFileMatcher = typeOfFilePattern.matcher(path);
        if (typeOfFileMatcher.find()) {
            if (".xml".equals(typeOfFileMatcher.group())) {
                factory = new XmlFileFactory();
                fileApplication = new Application(factory);
            }
        }
        return fileApplication;
    }
}
