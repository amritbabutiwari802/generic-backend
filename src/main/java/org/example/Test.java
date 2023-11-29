package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Hello world!");
        File xmlFile = new File("C:\\test.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        System.out.println("Root element is "+ doc.getDocumentElement().getNodeName());
        Element initializationNode = (Element) doc.getElementsByTagName("initialization").item(0);
        Element apiListElement = (Element) doc.getElementsByTagName("api-list").item(0);
        System.out.println("the value of "+ initializationNode.getNodeName()+" is "+ initializationNode.getTextContent());

        NodeList apiList = apiListElement.getElementsByTagName("api");
        for( int i = 0; i< apiList.getLength(); i++){
            Element nameNode = ((Element)apiList.item(i));
            System.out.println("value of api.name is "+
                    nameNode.getElementsByTagName("name").item(0).getTextContent());
        }

    }
}
