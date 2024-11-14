package com.uok.servelet_application.servelets;

import com.uok.servelet_application.javaClasses.Student;
import com.uok.servelet_application.javaClasses.writeXML;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet("/form_handling_servlet")
public class form_handling_servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = String.valueOf(new Date().getTime());
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dateofBirth = request.getParameter("dateOfBirth");
        String year = request.getParameter("year");
        String address = request.getParameter("address");
        String medium = request.getParameter("medium");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String grade = request.getParameter("grade");

        // Validate input fields
        if (name == null || name.isEmpty() ||
                gender == null || gender.isEmpty() ||
                dateofBirth == null || dateofBirth.isEmpty() ||
                year == null || year.isEmpty() ||
                address == null || address.isEmpty() ||
                email == null || email.isEmpty() ||
                medium == null || medium.isEmpty() ||
                phoneNumber == null || phoneNumber.isEmpty() ||
                grade == null || grade.isEmpty()) {

            response.sendRedirect("index.jsp?error=Invalid input. Please fill all fields.");
            return;
        }

        // Create a new Student object
        Student student = new Student(id, name, gender, dateofBirth, year, address, email, phoneNumber, grade , medium);

        // path of the xml file
        String xmlFilePath = getServletContext().getRealPath("/WEB-INF/Students.xml");
        System.out.println("XML File Path: " + xmlFilePath);

        // Save student data to XML
        writeXML.saveDataToXML(student, xmlFilePath);

        // Redirect to a success page
        response.sendRedirect("SubmitData.jsp?success=Data submitted");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String xmlFilePath = getServletContext().getRealPath("/WEB-INF/Students.xml");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<HashMap<String, String>> studentList = new ArrayList<>();
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList entries = document.getElementsByTagName("student");

            for (int i = 0; i < entries.getLength(); i++) {
                Element entry = (Element) entries.item(i);
                HashMap<String, String> student = new HashMap<>();

                student.put("id", entry.getElementsByTagName("id").item(0).getTextContent());
                student.put("name", entry.getElementsByTagName("name").item(0).getTextContent());
                student.put("gender", entry.getElementsByTagName("gender").item(0).getTextContent());
                student.put("dateOfBirth", entry.getElementsByTagName("dateOfBirth").item(0).getTextContent());
                student.put("year", entry.getElementsByTagName("year").item(0).getTextContent());
                student.put("address", entry.getElementsByTagName("address").item(0).getTextContent());
                student.put("email", entry.getElementsByTagName("email").item(0).getTextContent());
                student.put("phoneNumber", entry.getElementsByTagName("phoneNumber").item(0).getTextContent());
                student.put("grade", entry.getElementsByTagName("grade").item(0).getTextContent());

                studentList.add(student);
            }

            // Set the list as a request attribute
            request.setAttribute("studentList", studentList);

            // Forward to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewAndDelete.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            out.println("<p>Error reading XML data: " + e.getMessage() + "</p>");
        } finally {
            out.close();
        }
    }
}
