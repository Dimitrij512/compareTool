package main;

import java.io.IOException;
import services.Transform;


//TODO  It is test version
public class Main {
    public static void main(String[] args) throws IOException {
        Transform transform = new Transform();
        transform.createTree(transform.getXMLBean("sas"), null);
    }
}


