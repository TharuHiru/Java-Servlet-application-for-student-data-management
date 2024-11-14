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
                <th>Medium</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Grade</th>
                <th>Actions</th>
            </tr>

            <%
                List<HashMap<String, String>> studentList = (List<HashMap<String, String>>) request.getAttribute("studentList");
                if (studentList != null && !studentList.isEmpty()) {
                    for (HashMap<String, String> student : studentList) {
            %>
            <tr>
                <td><%= student.getOrDefault("id", "") %></td>
                <td><%= student.getOrDefault("name", "") %></td>
                <td><%= student.getOrDefault("gender", "") %></td>
                <td><%= student.getOrDefault("dateOfBirth", "") %></td>
                <td><%= student.getOrDefault("year", "") %></td>
                <td><%= student.getOrDefault("address", "") %></td>
                <td><%= student.getOrDefault("medium", "") %></td>
                <td><%= student.getOrDefault("email", "") %></td>
                <td><%= student.getOrDefault("phoneNumber", "") %></td>
                <td><%= student.getOrDefault("grade", "") %></td>
                <td>
                    <a href="form_handling_servlet?action=view&id=<%= student.get("id") %>">
                        <i class="fa fa-eye" style="color: darkslategray;"></i>
                    </a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="11">No data available</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>

    <!-- Container for the Student Form -->
    <div class="form-container">
        <h3>View Student</h3>
        <%
            HashMap<String, String> selectedStudent = (HashMap<String, String>) request.getAttribute("selectedStudent");
        %>
        <form action="form_handling_servlet" method="post">
            <label for="id">Student ID</label>
            <input type="text" id="id" name="id" value="<%= selectedStudent != null ? selectedStudent.get("id") : "" %>" required readonly/>

            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="<%= selectedStudent != null ? selectedStudent.get("name") : "" %>" required/>

            <label>Gender:</label>
            <div class="gender-input">
                <label for="male">Male</label>
                <input type="radio" id="male" name="gender" value="male" <%= selectedStudent != null && "male".equals(selectedStudent.get("gender")) ? "checked" : "" %>/>
                <label for="female">Female</label>
                <input type="radio" id="female" name="gender" value="female" <%= selectedStudent != null && "female".equals(selectedStudent.get("gender")) ? "checked" : "" %>/>
            </div>

            <label for="dateOfBirth">Date of Birth</label>
            <input type="date" id="dateOfBirth" name="dateOfBirth" value="<%= selectedStudent != null ? selectedStudent.get("dateOfBirth") : "" %>" required/>

            <label for="year">Enrolled Year</label>
            <select id="year" name="year" required>
                <option value="">Select Year</option>
                <option value="1998" <%= selectedStudent != null && "1998".equals(selectedStudent.get("year")) ? "selected" : "" %>>1998</option>
                <option value="1999" <%= selectedStudent != null && "1999".equals(selectedStudent.get("year")) ? "selected" : "" %>>1999</option>
                <option value="2000" <%= selectedStudent != null && "2000".equals(selectedStudent.get("year")) ? "selected" : "" %>>2000</option>
            </select>

            <label for="address">Address</label>
            <input type="text" id="address" name="address" value="<%= selectedStudent != null ? selectedStudent.get("address") : "" %>" required/>

            <label for="medium">Learning Medium</label>
            <select id="medium" name="medium" required>
                <option value="">Select Medium</option>
                <option value="Sinhala" <%= selectedStudent != null && "Sinhala".equals(selectedStudent.get("medium")) ? "selected" : "" %>>Sinhala</option>
                <option value="English" <%= selectedStudent != null && "English".equals(selectedStudent.get("medium")) ? "selected" : "" %>>English</option>
            </select>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="<%= selectedStudent != null ? selectedStudent.get("email") : "" %>" required/>

            <label for="phoneNumber">Phone Number</label>
            <input type="number" id="phoneNumber" name="phoneNumber" value="<%= selectedStudent != null ? selectedStudent.get("phoneNumber") : "" %>" required/>

            <label for="grade">Grade</label>
            <input type="text" id="grade" name="grade" value="<%= selectedStudent != null ? selectedStudent.get("grade") : "" %>" required/>

            <input type="submit" value="Update" />
        </form>
    </div>


</div>

</body>
</html>
