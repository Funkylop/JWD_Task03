package by.epam_training.homework03.main;

import by.epam_training.homework03.entity.Element;

public class PrintInfoFile {
    public static void print(Element element) {
        for (Element node : element.getChildNodes()) {
            if (!node.getChildNodes().isEmpty()){
                print(node);
            }else{
                System.out.println(node.getContent());
            }
        }
    }
}
