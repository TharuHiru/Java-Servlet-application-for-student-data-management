package com.uok.servelet_application;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
}
