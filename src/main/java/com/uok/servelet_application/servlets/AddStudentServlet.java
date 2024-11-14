package com.uok.servelet_application.servlets;

import com.uok.servelet_application.javaClasses.Student;
import com.uok.servelet_application.javaClasses.writeXML;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet("/form_handling_servlet")
public class AddStudentServlet extends HttpServlet {

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


}
