<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<html>
<head>
    <title>Student Data</title>
</head>
<body>
<h2>Submitted Data</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Gender</th>
        <th>Date of Birth</th>
        <th>Year</th>
        <th>Address</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Grade</th>
    </tr>

    <%
        List<HashMap<String, String>> studentList = (List<HashMap<String, String>>) request.getAttribute("studentList");
        if (studentList != null) {
            for (HashMap<String, String> student : studentList) {
    %>
    <tr>
        <td><%= student.get("id") %></td>
        <td><%= student.get("name") %></td>
        <td><%= student.get("gender") %></td>
        <td><%= student.get("dateOfBirth") %></td>
        <td><%= student.get("year") %></td>
        <td><%= student.get("address") %></td>
        <td><%= student.get("email") %></td>
        <td><%= student.get("phoneNumber") %></td>
        <td><%= student.get("grade") %></td>
    </tr>
    <%
        } // End of for loop
    } else {
    %>
    <tr>
        <td colspan="9">No data available</td>
    </tr>
    <%
        } // End of if statement
    %>
</table>
</body>
</html>
