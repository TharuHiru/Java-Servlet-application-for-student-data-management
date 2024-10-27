package com.uok.servelet_application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XMLHandler {

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
}
