package by.epam_training.homework03.main;

import by.epam_training.homework03.configuration.FileApplication;
import by.epam_training.homework03.entity.Element;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileApplication fileApplication = FileApplication.configurationFile("/breakfast-menu.xml");

        Element elementStructure;

        List<String> file = fileApplication.executeRead();
        elementStructure = fileApplication.executeBuildFileStructure(file);

        PrintInfoFile.print(elementStructure);
    }
}
