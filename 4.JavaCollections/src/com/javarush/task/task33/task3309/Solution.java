package com.javarush.task.task33.task3309;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;


/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {

        //Получим XML представление класса
        StringWriter writer = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);
        InputSource inSource = new InputSource(new StringReader(writer.toString()));

        //Переменные, используемые в работе
        Document doc = null;
        StringWriter outputWriter = null;

        //Создадим DOM на основании XML представления класса
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(inSource);
            Node root = doc.getDocumentElement();

            //Обработаем XML документ
            process(doc, tagName, comment);
            changeTextToCDATA(root, doc);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        //выведем полученный результат
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            DOMSource domSource = new DOMSource(doc);
            outputWriter = new StringWriter();
            StreamResult outputResult = new StreamResult(outputWriter);
            transformer.transform(domSource, outputResult);

        } catch (TransformerConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }

        return outputWriter == null ? "Документ отсутствует" : outputWriter.toString();
    }

    private static void process(Document doc, String tagName, String tagComment) {
        NodeList nodeList = doc.getElementsByTagName(tagName);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node goalNode = nodeList.item(i);
            if (goalNode.getNodeType() == Element.ELEMENT_NODE) {
                Element goalElement = (Element) goalNode;
                Comment comment = doc.createComment(tagComment);
                goalElement.getParentNode().insertBefore(comment, goalElement);
            }
        }
    }

    private static void changeTextToCDATA(Node root, Document doc) {
        if (root.getNodeType() == Node.TEXT_NODE) {
            String CDATAStr = root.getNodeValue();
            if (CDATAStr.matches(".*[<>&'\"].*")) {
                root.getParentNode().replaceChild(doc.createCDATASection(CDATAStr), root);
            }
        }

        for (Node child = root.getFirstChild(); child != null; child = child.getNextSibling()) {
            changeTextToCDATA(child, doc);
        }
    }

    public static void main(String[] args) throws JAXBException {
        String result = null;
        First first = new First();

        result = toXmlWithComment(first, "second", "My comment");
        System.out.println(result);
    }

    @XmlRootElement(name = "first")
    public static class First {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
        @XmlElement(name = "second")
        public String item3 = "";
        @XmlElement(name = "third")
        public String item4;
        @XmlElement(name = "forth")
        public Second[] third = new Second[]{new Second()};
        @XmlElement(name = "fifth")
        public String item5 = "need CDATA because of \"";
    }

    public static class Second {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
    }
}
