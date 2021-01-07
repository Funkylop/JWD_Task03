package by.epam.homework3.main;

import by.epam.homework3.entity.Node;
import by.epam.homework3.entity.ParserXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
 public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
    Node node = new Node();
     ParserXML parser = new ParserXML();
     node = parser.parseXML(node);
 }
}
