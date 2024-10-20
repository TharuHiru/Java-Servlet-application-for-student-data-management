package com.uok.servelet_application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XMLHandler {

    public static void saveDataToXML(String id, String userData, String xmlFilePath) throws IOException {
        File xmlFile = new File(xmlFilePath);

        // If the file doesn't exist, create it and add the root element
        if (!xmlFile.exists()) {
            try (FileWriter writer = new FileWriter(xmlFile)) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<entries>\n"); // Start root element
                writer.write("\t<entry>\n");
                writer.write("\t\t<id>" + id + "</id>\n");
                writer.write("\t\t<data>" + userData + "</data>\n");
                writer.write("\t</entry>\n");
                writer.write("</entries>\n"); // End root element
            }
        } else {
            // If the file exists, read the current content to avoid breaking the structure
            StringBuilder content = new StringBuilder();
            try (Scanner scanner = new Scanner(xmlFile)) {
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
            }

            // Check if the root element is intact
            if (content.indexOf("</entries>") != -1) {
                // Replace the end of the root element to insert the new entry
                content.insert(content.lastIndexOf("</entries>"), "\t<entry>\n" +
                        "\t\t<id>" + id + "</id>\n" +
                        "\t\t<data>" + userData + "</data>\n" +
                        "\t</entry>\n");

                // Write the updated content back to the XML file
                try (FileWriter writer = new FileWriter(xmlFile)) {
                    writer.write(content.toString());
                }
            } else {
                throw new IOException("Invalid XML structure: missing root element.");
            }
        }
    }
}
