package services;

import java.io.IOException;

import beans.Model;
import beans.XmlBean;


//TODO  It is test version
public class Main {
    public static void main(String[] args) throws IOException {

        Model model = new Model();
        model.setXmiName("Test");
        model.setVersion("xxxxxx");
        Model mode2 = new Model();
        mode2.setXmiName("Test");
        mode2.setVersion("xxxxdddxx");

        System.out.println(mode2.equals(model));

        Transform transform = new Transform();
        XmlBean firstFile = transform.getXMLBean(Main.class.getClassLoader().getResource("testXML.xml").getFile());
        XmlBean secondFile = transform.getXMLBean(Main.class.getClassLoader().getResource("testXML2.xml").getFile());

        transform.createTree(firstFile, secondFile);
    }
}


