package by.epam.homework3.entity;

import com.sun.security.jgss.GSSUtil;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.List;

public class ParserXML {

    public Node parseXML(Node node) throws IOException {
        XmlReader reader = new XmlReader();
        Node root;
        List<String> xmlFile;
        List<String> structuredXmlFile;

        xmlFile = reader.readXml();
        System.out.println(xmlFile);
        structuredXmlFile = reader.parser(xmlFile);
        System.out.println(structuredXmlFile);
        root = reader.getXmlStructure(structuredXmlFile);
        return root;
    }

}