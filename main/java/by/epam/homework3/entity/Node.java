package by.epam.homework3.entity;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<Attribute> attributes;
    private List<Node> childNodes = new ArrayList<>();
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void addChildNode(Node node){
        childNodes.add(node);
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

}
