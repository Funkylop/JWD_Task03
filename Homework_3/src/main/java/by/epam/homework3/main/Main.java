package by.epam.homework3.main;

import by.epam.homework3.Parser.FileDataParser;
import by.epam.homework3.Reader.FileDataReader;
import by.epam.homework3.entity.Node;
import by.epam.homework3.service.Application;

import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        Application application = Application.configurationFile("prop.xml");
        InformationOutput informationOuter = new InformationOutput();
        Node root;

        FileDataParser parser = application.getFileDataParser();
        FileDataReader reader = application.getFileDataReader();
        List<String> data = parser.structureData(reader.read("prop.xml"));
        root = parser.getFileStructure(data);

        informationOuter.informationAboutStructure(root);
    }
}
