package by.epam.homework3.main;

import by.epam.homework3.entity.Attribute;
import by.epam.homework3.entity.Node;

public class InformationOutput {
    private static int k = 0;
    public void informationAboutStructure(Node node){
        for(Node tempNode : node.getChildNodes()) {
            for (Attribute attribute : tempNode.getAttributes()) {

                if (tempNode.getName().contains("food") && k != 0) {
                    System.out.println();
                }
                k++;
                attribute.outAttributes(attribute.getAttribute());
            }
            if (!"".equals(tempNode.getContent())) {
                if (tempNode.getName().contains("name")) {
                    System.out.print(tempNode.getContent());
                } else if (tempNode.getName().contains("price")) {
                    System.out.println(" - " + tempNode.getContent());
                }
                else  System.out.print(tempNode.getContent());
            }
            informationAboutStructure(tempNode);
        }
    }
}
