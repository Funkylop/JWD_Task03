package by.epam.homework3.entity;

import java.util.HashMap;
import java.util.Iterator;
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

    public void outAttributes(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String> pair = iterator.next();
            String key = pair.getKey();
            String value = pair.getValue();
            System.out.println(key + ":" + value);
        }
    }
    public void setAttribute(Map<String, String> attribute) {
        this.attribute = attribute;
    }
}