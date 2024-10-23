package com.uok.servelet_application;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/form_handling_servlet")
public class form_handling_servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userData = request.getParameter("data");

        if (userData == null || userData.isEmpty()) {
            response.sendRedirect("index.jsp?error=Invalid input");
            return;
        }

        String id = String.valueOf(new Date().getTime());

        // Use getServletContext().getRealPath() to get the actual path for the XML file
        String xmlFilePath = getServletContext().getRealPath("/WEB-INF/data.xml");
        System.out.println("XML File Path: " + xmlFilePath);

        XMLHandler.saveDataToXML(id, userData, xmlFilePath); // Update method to accept the path

        // Redirect to a success page
        response.sendRedirect("index.jsp?success=Data submitted");
    }

    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String xmlFilePath = getServletContext().getRealPath("/WEB-INF/data.xml");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Prepare XML document
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Get all <entry> elements
            NodeList entries = document.getElementsByTagName("entry");

            // Start HTML table to display the data
            out.println("<html><body>");
            out.println("<h2>Submitted Data</h2>");
            out.println("<table border='1'><tr><th>ID</th><th>Data</th></tr>");

            // Loop through the <entry> elements and extract <id> and <data>
            for (int i = 0; i < entries.getLength(); i++) {
                Element entry = (Element) entries.item(i);
                String id = entry.getElementsByTagName("id").item(0).getTextContent();
                String data = entry.getElementsByTagName("data").item(0).getTextContent();

                // Add rows to table
                out.println("<tr><td>" + id + "</td><td>" + data + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<p>Error reading XML data: " + e.getMessage() + "</p>");
        } finally {
            out.close();
        }
    }
}
