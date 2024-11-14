package com.uok.servelet_application.javaClasses;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class writeXML {

    public static void saveDataToXML(Student student , String xmlFilePath) throws IOException {
        File xmlFile = new File(xmlFilePath);

        // If the file doesn't exist, create it and add the root element
        if (!xmlFile.exists()) {
            try (FileWriter writer = new FileWriter(xmlFile)) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<Students>\n");
                writer.write("\t<student>\n");
                writer.write("\t\t<id>" + student.getId() + "</id>\n");
                writer.write("\t\t<name>" + student.getName() + "</name>\n");
                writer.write("\t\t<gender>" + student.getGender() + "</gender>\n");
                writer.write("\t\t<dateOfBirth>" + student.getDateOfBirth() + "</dateOfBirth>\n");
                writer.write("\t\t<year>" + student.getYear() + "</year>\n");
                writer.write("\t\t<address>" + student.getAddress() + "</address>\n");
                writer.write("\t\t<medium>" + student.getMedium() + "</medium>\n");
                writer.write("\t\t<email>" + student.getEmail() + "</email>\n");
                writer.write("\t\t<phoneNumber>" + student.getPhoneNumber() + "</phoneNumber>\n");
                writer.write("\t\t<grade>" + student.getGrade() + "</grade>\n");
                writer.write("\t</student>\n");
                writer.write("</Students>\n");
            }
        } else {
            // If the file exists, read the current content to avoid breaking the structure
            StringBuilder content = new StringBuilder();
            try (Scanner scanner = new Scanner(xmlFile)) {
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
            }

            if (content.indexOf("</Students>") != -1) {
                content.insert(content.lastIndexOf("</Students>"),
                        "\t<student>\n" +
                                "\t\t<id>" + student.getId() + "</id>\n" +
                                "\t\t<name>" +student.getName()+ "</name>\n" +
                               "\t\t<gender>" + student.getGender() + "</gender>\n"+
                                "\t\t<dateOfBirth>" + student.getDateOfBirth() + "</dateOfBirth>\n"+
                                "\t\t<year>" + student.getYear() + "</year>\n"+
                                "\t\t<address>" + student.getAddress() + "</address>\n"+
                                "\t\t<medium>" + student.getMedium() + "</medium>\n" +
                                "\t\t<email>" + student.getEmail() + "</email>\n"+
                               "\t\t<phoneNumber>" + student.getPhoneNumber() + "</phoneNumber>\n"+
                                "\t\t<grade>" + student.getGrade() + "</grade>\n"+
                            "\t</student>\n");

                try (FileWriter writer = new FileWriter(xmlFile)) {
                    writer.write(content.toString());
                }
            } else {
                throw new IOException("Invalid XML structure: missing root element.");
            }
        }
    }
    // Method to delete a book entry by ID from the XML file
  /*  public static void deleteDataFromXML(String id, String xmlFilePath) throws IOException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            File xmlFile = new File(xmlFilePath);
            Document document;

            if (!xmlFile.exists()) {
                throw new IOException("XML file does not exist");
            }

            document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList students = document.getElementsByTagName("student");
            boolean Found = false;

            // Loop through the books and remove the one with matching ID
            for (int i = 0; i < students.getLength(); i++) {
                Element student = (Element) students.item(i);
                if (student.getElementsByTagName("id").item(0).getTextContent().equals(id)) {
                    student.getParentNode().removeChild(student);
                    Found = true;
                    break;
                }
            }

            if (!Found) {
                throw new IOException("Book with ID " + id + " not found");
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

        } catch (Exception e) {
            throw new IOException("Failed to delete data from XML", e);
        }
    }
*/
    public static void writeXMLFile(Document document, String xmlFilePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
