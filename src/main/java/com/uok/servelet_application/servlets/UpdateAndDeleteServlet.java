package com.uok.servelet_application.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet("/UpdateAndDelete_servlet")
public class UpdateAndDeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the data submitted by the FORM
        String studentId = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String year = request.getParameter("year");
        String address = request.getParameter("address");
        String medium = request.getParameter("medium");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String grade = request.getParameter("grade");

        // Validate input fields
        if (studentId == null || studentId.isEmpty() ||
                name == null || name.isEmpty() ||
                gender == null || gender.isEmpty() ||
                dateOfBirth == null || dateOfBirth.isEmpty() ||
                year == null || year.isEmpty() ||
                address == null || address.isEmpty() ||
                medium == null || medium.isEmpty() ||
                email == null || email.isEmpty() ||
                phoneNumber == null || phoneNumber.isEmpty() ||
                grade == null || grade.isEmpty()) {

            // If the data is not valide sent an error message
            response.sendRedirect("index.jsp?error=Invalid input. Please fill all fields.");
            return;
        }

        // set the path of the XML file
        String xmlFilePath = getServletContext().getRealPath("/WEB-INF/Students.xml");

        try {
            // Load the XML file
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Find the student element by ID

            //check all student elements
            NodeList students = document.getElementsByTagName("student");

            // initially student is not found
            boolean studentFound = false;

            // check all the student nodes to find the relevent student
            for (int i = 0; i < students.getLength(); i++) {
                Element studentElement = (Element) students.item(i);
                String currentId = studentElement.getElementsByTagName("id").item(0).getTextContent();

                // if the student is found , update the details
                if (currentId.equals(studentId)) {
                    studentElement.getElementsByTagName("name").item(0).setTextContent(name);
                    studentElement.getElementsByTagName("gender").item(0).setTextContent(gender);
                    studentElement.getElementsByTagName("dateOfBirth").item(0).setTextContent(dateOfBirth);
                    studentElement.getElementsByTagName("year").item(0).setTextContent(year);
                    studentElement.getElementsByTagName("address").item(0).setTextContent(address);
                    studentElement.getElementsByTagName("medium").item(0).setTextContent(medium);
                    studentElement.getElementsByTagName("email").item(0).setTextContent(email);
                    studentElement.getElementsByTagName("phoneNumber").item(0).setTextContent(phoneNumber);
                    studentElement.getElementsByTagName("grade").item(0).setTextContent(grade);

                    // set the student found to true
                    studentFound = true;
                    break;
                }
            }

            // if the student is found update the data in XML
            if (studentFound) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(xmlFile);
                transformer.transform(source, result);

                // After updating,read the X
                List<HashMap<String, String>> studentList = new ArrayList<>();
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
                    student.put("medium", entry.getElementsByTagName("medium").item(0).getTextContent());
                    student.put("email", entry.getElementsByTagName("email").item(0).getTextContent());
                    student.put("phoneNumber", entry.getElementsByTagName("phoneNumber").item(0).getTextContent());
                    student.put("grade", entry.getElementsByTagName("grade").item(0).getTextContent());
                    studentList.add(student);
                }

                // Set the updated student list in the request attribute
                request.setAttribute("studentList", studentList);
                request.setAttribute("successMessage", "Student details updated successfully.");
                request.getRequestDispatcher("viewAndDelete.jsp").forward(request, response);

            } else {
                response.sendRedirect("index.jsp?error=Student not found.");
            }
        } catch (Exception e) {
            response.sendRedirect("index.jsp?error=An error occurred: " + e.getMessage());
        }
    }

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

                // Iterate through the XML entries
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

                    // Check if the action is "view" and the ID matches
                    if ("view".equals(action) && student.get("id").equals(studentId)) {
                        selectedStudent = student;
                    }

                    studentList.add(student);
                }

                // Handle delete action
                if ("delete".equals(action) && studentId != null) {
                    boolean isDeleted = deleteStudentFromXML(xmlFilePath, studentId, request, response);

                    if (isDeleted) {
                        response.getWriter().println("<p>Student deleted successfully.</p>");
                    } else {
                        response.getWriter().println("<p>Failed to delete student. Student not found.</p>");
                    }
                    response.sendRedirect("viewAndDelete.jsp");
                    return;
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

        // Method to delete a student from the XML file
        private boolean deleteStudentFromXML(String xmlFilePath, String studentId, HttpServletRequest request, HttpServletResponse response) {
            try {
                File xmlFile = new File(xmlFilePath);
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(xmlFile);
                document.getDocumentElement().normalize();

                NodeList entries = document.getElementsByTagName("student");

                for (int i = 0; i < entries.getLength(); i++) {
                    Node studentNode = entries.item(i);
                    if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element studentElement = (Element) studentNode;
                        String id = studentElement.getElementsByTagName("id").item(0).getTextContent();

                        if (id.equals(studentId)) {
                            // Remove the student element
                            studentElement.getParentNode().removeChild(studentElement);

                            // Save the updated XML file
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            DOMSource source = new DOMSource(document);
                            StreamResult result = new StreamResult(new File(xmlFilePath));
                            transformer.transform(source, result);

                            // Re-read the XML and update the student list
                            List<HashMap<String, String>> studentList = new ArrayList<>();
                            NodeList newEntries = document.getElementsByTagName("student");

                            for (int j = 0; j < newEntries.getLength(); j++) {
                                Element entry = (Element) newEntries.item(j);
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
                                studentList.add(student);
                            }

                            // Set the updated student list in the request attribute
                            request.setAttribute("studentList", studentList);
                            request.setAttribute("successMessage", "Student deleted successfully.");
                            // Forward to the view and delete page
                            request.getRequestDispatcher("viewAndDelete.jsp").forward(request, response);
                            return true; // Deletion successful
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false; // Deletion failed
        }

}
