package by.epam.homework3.entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlReader {
    public static final String NAME_TEG_PATTERN;
    public static final String ATTRIBUTE_PATTERN;
    public static final String CONTENT_PATTERN;
    public static final String END_TEG_PATTERN;

    static {
        NAME_TEG_PATTERN = "<\\w+\\/*>|<[^/].+>";
        ATTRIBUTE_PATTERN = "\\w+=\".+\"\\s*";
        CONTENT_PATTERN = ">\\s*\\w.*?<|>\\W*\\d.*?\\s*<";
        END_TEG_PATTERN = "</.+?>|<\\w+/>";
    }
    public List<String> readXml() throws IOException {
        List<String> appliancesList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(("prop.xml")));
            String stringOfFile;
            while ((stringOfFile = bufferedReader.readLine()) != null) {
                appliancesList.add(stringOfFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
        return appliancesList;
    }
    public List<String> parser(List<String> stringsXml){

        Pattern patternName = Pattern.compile(NAME_TEG_PATTERN);
        Pattern patternContent = Pattern.compile(CONTENT_PATTERN);
        Pattern patternEndTeg = Pattern.compile(END_TEG_PATTERN);

        List<String> strings = new ArrayList<>();
        for (String stringXml : stringsXml){
            Matcher matcherName = patternName.matcher(stringXml);
            Matcher matcherContent = patternContent.matcher(stringXml);
            Matcher matcherEndTeg = patternEndTeg.matcher(stringXml);

            while (matcherName.find()) {
                strings.add(matcherName.group());
            }
            while(matcherContent.find()){
                strings.add(matcherContent.group());
            }
            while (matcherEndTeg.find()){
                strings.add(matcherEndTeg.group());
            }
        }
        return strings;
    }

    public Node getXmlStructure(List<String> structuredXmlFile){
        String nameTeg, content, endNameTeg;
        List<Attribute> attributes;
        Stack<Node> nodes = new Stack<>();
        for(String xmlString: structuredXmlFile){
            Node node = new Node();
            nameTeg = getNameTeg(xmlString);
            attributes = getAttribute(xmlString);
            content = getContent(xmlString);
            endNameTeg = getEndTeg(xmlString);

            node.setName(nameTeg);
            node.setAttributes(attributes);
            node.setContent(content);

            if (!"".equals(node.getContent())){
                nodes.peek().setContent(node.getContent());
            }

            if ("".equals(node.getContent()) && "".equals(endNameTeg)){
                nodes.add(node);
            }

            String nameLastNode = nodes.peek().getName();

            if (!"".equals(endNameTeg)){
                if ((nameLastNode.charAt(0) + "/" + nameLastNode.substring(1)).equals(endNameTeg)
                        || nameLastNode.equals(endNameTeg)){
                    if (nodes.size()==1){
                        return nodes.pop();
                    }else{
                        node = nodes.pop();
                        nodes.peek().addChildNode(node);
                    }
                } else {
                    nodes.add(node);
                }
            }
        }
        return nodes.pop();
    }

    public String getNameTeg(String xmlString){
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

    public List<Attribute> getAttribute(String xmlString){
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

    public String getEndTeg(String xmlString){
        Pattern patternEndTeg = Pattern.compile(END_TEG_PATTERN);
        Matcher matcherEndTeg = patternEndTeg.matcher(xmlString);
        String teg;
        if(matcherEndTeg.find()){
            teg = matcherEndTeg.group();
        }
        else teg = "";
        return teg;
    }

    public String getContent(String xmlString){
        Pattern patternContent = Pattern.compile(CONTENT_PATTERN);
        Matcher matcherContent= patternContent.matcher(xmlString);
        String content ="";

        if(matcherContent.find()){
            content = matcherContent.group().substring(1, matcherContent.group().length()-1);
        }
        return content;
    }
}