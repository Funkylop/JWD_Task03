package by.epam.homework3.entity;

import java.util.HashMap;
import java.util.Map;

public class Attribute {

    private Map<String, String> attribute = new HashMap<>();

    public Attribute() {
    }

    public Attribute(Map<String, String> attribute) {
        this.attribute = attribute;
    }

    public Map<String, String> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, String> attribute) {
        this.attribute = attribute;
    }
}