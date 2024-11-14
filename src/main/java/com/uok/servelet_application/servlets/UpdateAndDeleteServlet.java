package com.uok.servelet_application.servlets;

import jakarta.servlet.RequestDispatcher;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/UpdateAndDelete_servlet")
public class UpdateAndDeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String xmlFilePath = getServletContext().getRealPath("/WEB-INF/Students.xml");
        response.setContentType("text/html");
        String action = request.getParameter("action");
        String studentId = request.getParameter("id");

        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList entries = document.getElementsByTagName("student");
            HashMap<String, String> selectedStudent = null;
            List<HashMap<String, String>> studentList = new ArrayList<>();

            for (int i = 0; i < entries.getLength(); i++) {
                Element entry = (Element) entries.item(i);
                HashMap<String, String> student = new HashMap<>();

                student.put("id", entry.getElementsByTagName("id").item(0).getTextContent());
                student.put("name", entry.getElementsByTagName("name").item(0).getTextContent());
                student.put("gender", entry.getElementsByTagName("gender").item(0).getTextContent());
                student.put("dateOfBirth", entry.getElementsByTagName("dateOfBirth").item(0).getTextContent());
                student.put("year", entry.getElementsByTagName("year").item(0).getTextContent());
                student.put("address", entry.getElementsByTagName("address").item(0).getTextContent());
                student.put("medium", entry.getElementsByTagName("medium").item(0).getTextContent());
                student.put("email", entry.getElementsByTagName("email").item(0).getTextContent());
                student.put("phoneNumber", entry.getElementsByTagName("phoneNumber").item(0).getTextContent());
                student.put("grade", entry.getElementsByTagName("grade").item(0).getTextContent());

                if (action != null && action.equals("view") && student.get("id").equals(studentId)) {
                    selectedStudent = student;
                }

                studentList.add(student);
            }

            if (selectedStudent != null) {
                request.setAttribute("selectedStudent", selectedStudent);
            }
            request.setAttribute("studentList", studentList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewAndDelete.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.getWriter().println("<p>Error: " + e.getMessage() + "</p>");
        }
    }

}
