package by.epam.homework3.Parser;

import by.epam.homework3.entity.Node;

import java.util.List;

public interface FileDataParser {
        List<String> structureData(List<String> stringsFile);
        Node getFileStructure(List<String> structuredFile);
}
