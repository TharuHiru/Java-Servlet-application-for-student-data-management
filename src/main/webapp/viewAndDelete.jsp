<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet" href="Styles/viewPage.css">

<html>
<head>
    <title>Student Data</title>
</head>
<body>

<h2>Student Management</h2>

<div class="flex-container">

    <!-- Container for the Student Data Table -->
    <div class="table-container">
        <h3>Student Information</h3>
        <table>
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
                <th>Actions</th>
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
                <td>
                    <a href="form_handling_servlet?action=delete&id=<%= student.get("id") %>" onclick="return confirm('Are you sure you want to delete this student?');">
                        <i class="fa fa-trash" style="color: red;"></i>
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="10">No data available</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>

    <!-- Container for the Student Form -->
    <div class="form-container">
        <h3>Update Student</h3>
        <form action="form_handling_servlet" method="post">
            <label for="id">Student ID</label>
            <input type="text" id="id" name="id" required readonly/>

            <label for="name">Name</label>
            <input type="text" id="name" name="name" required />

            <label>Gender:</label>
            <div class="gender-input">
                <label for="male">Male</label>
                <input type="radio" id="male" name="gender" value="male">
                <label for="female">Female</label>
                <input type="radio" id="female" name="gender" value="female">
            </div>

            <label for="dateOfBirth">Date of Birth</label>
            <input type="date" id="dateOfBirth" name="dateOfBirth" required />

            <label for="year">Enrolled Year</label>
            <select id="year" name="year" required>
                <option value="">Select Year</option>
                <option value="1998">1998</option>
                <option value="1999">1999</option>
                <option value="2000">2000</option>
                <option value="2001">2001</option>
                <option value="2002">2002</option>
                <option value="2003">2003</option>
            </select>

            <label for="address">Address</label>
            <input type="text" id="address" name="address" required />

            <label for="email">Email</label>
            <input type="email" id="email" name="email" required />

            <label for="phoneNumber">Phone Number</label>
            <input type="number" id="phoneNumber" name="phoneNumber" required />

            <label for="grade">Grade</label>
            <input type="text" id="grade" name="grade" required />

            <input type="submit" value="Update" />
        </form>
    </div>
</div>

</body>
</html>
