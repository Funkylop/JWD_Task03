package by.epam.homework3.Parser.impl;

import by.epam.homework3.Parser.FileDataParser;
import by.epam.homework3.entity.Attribute;
import by.epam.homework3.entity.Node;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParser implements FileDataParser {
    public static final String NAME_TEG_PATTERN = "<\\w+>|<[^/].+?>";
    public static final String ATTRIBUTE_PATTERN = "\\w+=\".+\"\\s*";
    public static final String CONTENT_PATTERN = ">\\s*\\w.*?<|>\\W*\\d.*?\\s*<|>\\s*\\w.*?.$|\\s\\s([A-z]\\w+)\\s*\\w.*?<|\\s\\s([A-z]\\w+)\\s*\\w.*";
    public static final char TRIANGULAR_BRACKET_RIGHT = '>';
    public static final char TRIANGULAR_BRACKET_LEFT = '<';
    public static final String END_TEG_PATTERN = "</.+?>|<\\w+/>";
    public static final char WHITESPACE = ' ';

    public List<String> structureData(List<String> stringsXml) {

        Pattern patternName = Pattern.compile(NAME_TEG_PATTERN);
        Pattern patternContent = Pattern.compile(CONTENT_PATTERN);
        Pattern patternEndTeg = Pattern.compile(END_TEG_PATTERN);

        List<String> strings = new ArrayList<>();
        for (String stringXml : stringsXml) {
            Matcher matcherName = patternName.matcher(stringXml);
            Matcher matcherContent = patternContent.matcher(stringXml);
            Matcher matcherEndTeg = patternEndTeg.matcher(stringXml);

            while (matcherName.find()) {
                strings.add(matcherName.group());
            }
            while (matcherContent.find()) {
                strings.add(matcherContent.group());
            }
            while (matcherEndTeg.find()) {
                strings.add(matcherEndTeg.group());
            }
        }
        return strings;
    }

    public Node getFileStructure(List<String> structuredXmlFile) {
        String nameTeg, content, endNameTeg;
        List<Attribute> attributes;
        LinkedList<Node> nodes1 = new LinkedList<>();

        for (String xmlString : structuredXmlFile) {
            Node node = new Node();
            nameTeg = takeNameTeg(xmlString);
            attributes = takeAttribute(xmlString);
            content = takeContent(xmlString);
            endNameTeg = takeEndTeg(xmlString);

            node.setName(nameTeg);
            node.setAttributes(attributes);
            node.setContent(content);

            if (!"".equals(node.getContent())) {
                if (nodes1.peek() != null) {
                    nodes1.peek().setContent(node.getContent());
                }
            }

            if ("".equals(node.getContent()) && "".equals(endNameTeg)) {
                nodes1.addFirst(node);
            }

            String nameLastNode = null;
            if (nodes1.peek() != null) {
                nameLastNode = nodes1.peek().getName();
            }


            if (!"".equals(endNameTeg)) {
                {
                    if (nameLastNode != null) {
                        if ((nameLastNode.charAt(0) + "/" + nameLastNode.substring(1)).equals(endNameTeg)
                                || nameLastNode.equals(endNameTeg)) {
                            if (nodes1.size() == 1) {
                                return nodes1.pop();
                            } else {
                                node = nodes1.pop();
                                if (nodes1.peek() != null) {
                                    nodes1.peek().addChildNode(node);
                                }
                            }
                        } else {
                            nodes1.addFirst(node);
                        }
                    }
                }
            }
        }

        return nodes1.pop();
    }


    public String takeNameTeg(String xmlString){
        String name = "";
        Pattern patternName = Pattern.compile(NAME_TEG_PATTERN);
        Matcher matcherName = patternName.matcher(xmlString);

        if (matcherName.find()){
            int indexWithSpace;
            if ((indexWithSpace = matcherName.group().indexOf(" "))!=-1){
                name = matcherName.group().substring(0, indexWithSpace);
            }else{
                name = matcherName.group().substring(0, matcherName.group().length()-1);
            }
            name+='>';
        }

        return name;
    }

    public List<Attribute> takeAttribute(String xmlString){
        String nameAttribute;
        String contentAttribute;
        Pattern patternAttribute = Pattern.compile(ATTRIBUTE_PATTERN);
        Matcher matcherAttribute = patternAttribute.matcher(xmlString);
        List<Attribute> attributes = new ArrayList<>();
        Map<String, String> attribute = new HashMap<>();
        String[] splitsString;

        if(matcherAttribute.find()){
            splitsString = matcherAttribute.group().split("=");
            nameAttribute = splitsString[0];
            splitsString = splitsString[1].split("\"");
            contentAttribute = splitsString[1];
            attribute.put(nameAttribute, contentAttribute);
            attributes.add(new Attribute(attribute));
        }

        return attributes;
    }

    public String takeEndTeg(String xmlString){
        Pattern patternEndTeg = Pattern.compile(END_TEG_PATTERN);
        Matcher matcherEndTeg = patternEndTeg.matcher(xmlString);
        String teg;
        if(matcherEndTeg.find()){
            teg = matcherEndTeg.group();
        }
        else teg = "";
        return teg;
    }

    public String takeContent(String xmlString){
        Pattern patternContent = Pattern.compile(CONTENT_PATTERN);
        Matcher matcherContent= patternContent.matcher(xmlString);
        String content ="";
        int i = 0, j;

        if(matcherContent.find()) {
            content = matcherContent.group();
            j = content.length() - 1;
            while (content.charAt(i) == WHITESPACE || content.charAt(i) == TRIANGULAR_BRACKET_LEFT || content.charAt(i) == TRIANGULAR_BRACKET_RIGHT) i++;
            while (content.charAt(j) == WHITESPACE || content.charAt(j) == TRIANGULAR_BRACKET_LEFT || content.charAt(j) == TRIANGULAR_BRACKET_RIGHT) j--;
            content = matcherContent.group().substring(i, j+1);
        }
        return content;
    }

}