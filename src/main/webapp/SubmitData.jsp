<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Information Form</title>
    <link rel="stylesheet" href="Styles/submitPage.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="form-container">
    <h2>Submit Data</h2>
    <form action="form_handling_servlet" method="post">

        <label for="name">Name</label>
        <input type="text" id="name" name="name" required />

        <label>Gender:</label>
        <div style="display: flex; justify-content: center; align-items: center">
            <div class="gender-input">
                <input type="radio" id="male" name="gender" value="male">
                <label for="male">Male</label> &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" id="female" name="gender" value="female">
                <label for="female">Female</label>
            </div>
        </div>

        <label for="dateOfBirth">Date of Birth</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" required />

        <label for="year">Enrolled Year</label>
        <select id="year" name="year" required>
            <option value="">Select Year</option>
            <option value="2018">2018</option>
            <option value="2019">2019</option>
            <option value="2020">2020</option>
            <option value="2021">2021</option>
            <option value="2022">2022</option>
            <option value="2023">2023</option>
        </select>

        <label for="address">Address</label>
        <input type="text" id="address" name="address" required />

        <label for="medium">Learning Medium</label>
        <select id="medium" name="medium" required>
            <option value="">Select Medium</option>
            <option value="Sinhala">Sinhala</option>
            <option value="English">English</option>
        </select>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" required />

        <label for="phoneNumber">Phone Numbers</label>
        <input type="number" id="phoneNumber" name="phoneNumber" required />

        <label for="grade">Grade</label>
        <input type="text" id="grade" name="grade" required />

        <input type="submit" value="Submit" />
    </form>

    <%-- Toast Notification for Success Message --%>
    <%
        String success = request.getParameter("success");
        if (success != null && success.equals("Data submitted")) {
    %>
    <div class="toast show position-fixed top-0 end-0 m-3">
        <div class="toast-header bg-success text-white">
            <strong class="me-auto">Success</strong>
            <button type="button" class="btn-close" data-bs-dismiss="toast"></button>
        </div>
        <div class="toast-body">
            Data submitted successfully!
        </div>
    </div>
    <% } %>
</div>
</body>
</html>
