<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Information</title>
    <link rel="stylesheet" href="Styles/styles.css"> <!-- Link your stylesheet for styling -->
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h2>Submitted Student Data</h2>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Gender</th>
        <th>Date of Birth</th>
        <th>Enrolled Year</th>
        <th>Address</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Grade</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Assuming you have a List or array of student data
        // Replace 'studentList' with your actual data source
        List<Student> studentList = (List<Student>) request.getAttribute("studentList");
        for (Student student : studentList) {
    %>
    <tr>
        <td><%= student.getName() %></td>
        <td><%= student.getGender() %></td>
        <td><%= student.getDateOfBirth() %></td>
        <td><%= student.getYear() %></td>
        <td><%= student.getAddress() %></td>
        <td><%= student.getEmail() %></td>
        <td><%= student.getPhoneNumber() %></td>
        <td><%= student.getGrade() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>
