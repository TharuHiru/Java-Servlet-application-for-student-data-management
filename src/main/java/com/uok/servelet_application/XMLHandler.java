package com.uok.servelet_application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XMLHandler {

    public static void saveDataToXML(String id, String name, String gender,String dateOfBirth,String year, String address , String email , String phoneNumber , String grade , String xmlFilePath) throws IOException {
        File xmlFile = new File(xmlFilePath);

        // If the file doesn't exist, create it and add the root element
        if (!xmlFile.exists()) {
            try (FileWriter writer = new FileWriter(xmlFile)) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<Students>\n");
                writer.write("\t<student>\n");
                writer.write("\t\t<id>" + id + "</id>\n");
                writer.write("\t\t<name>" + name + "</name>\n");
                writer.write("\t\t<gender>" + gender + "</gender>\n");
                writer.write("\t\t<dateOfBirth>" + dateOfBirth + "</dateOfBirth>\n");
                writer.write("\t\t<year>" + year + "</year>\n");
                writer.write("\t\t<address>" + address + "</address>\n");
                writer.write("\t\t<email>" + email + "</email>\n");
                writer.write("\t\t<phoneNumber>" + phoneNumber + "</phoneNumber>\n");
                writer.write("\t\t<grade>" + grade + "</grade>\n");
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
                                "\t\t<id>" + id + "</id>\n" +
                                "\t\t<name>" +name+ "</name>\n" +
                               "\t\t<gender>" + gender + "</gender>\n"+
                                "\t\t<dateOfBirth>" + dateOfBirth + "</dateOfBirth>\n"+
                                "\t\t<year>" + year + "</year>\n"+
                                "\t\t<address>" + address + "</address>\n"+
                                "\t\t<email>" + email + "</email>\n"+
                               "\t\t<phoneNumber>" + phoneNumber + "</phoneNumber>\n"+
                                "\t\t<grade>" + grade + "</grade>\n"+
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
