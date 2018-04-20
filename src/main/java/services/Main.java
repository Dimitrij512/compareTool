package services;

import java.io.IOException;

import beans.Model;
import beans.XmlBean;


//TODO  It is test version
public class Main {
    public static void main(String[] args) throws IOException {

        Transform transform = new Transform();
        XmlBean firstFile = transform.getXMLBean(Main.class.getClassLoader().getResource("testXML.xml").getFile());
        XmlBean secondFile = transform.getXMLBean(Main.class.getClassLoader().getResource("testXML2.xml").getFile());

        transform.createTree(firstFile, secondFile);
    }
}


