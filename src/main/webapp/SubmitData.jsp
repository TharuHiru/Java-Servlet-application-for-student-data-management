<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Student Information Form</title>
  <link rel="stylesheet" href="Styles/styles.css">
</head>
<body>
<div class="form-container">
  <h2>Submit Data</h2>
  <form action="form_handling_servlet" method="post">
    <label for="studentID">Student ID</label>
    <input type="text" id="studentID" name="studentID" required />

    <label for="name">Name</label>
    <input type="text" id="name" name="name" required />

    <label>Gender:</label>
    <br><br>
<div style=" display: flex;
    justify-content: center;
    align-items: center">
      <label for="male" style="font-weight: normal">Male</label>
      <input type="radio" id="male" name="gender" value="male">

      <label for="female" style="font-weight: normal">Female</label>
      <input type="radio" id="female" name="gender" value="female">
</div>

    <label for="dateOfBirth">Date of Birth</label>
    <input type="date" id="dateOfBirth" name="dateOfBirth" required />

    <label for="address">Address</label>
    <input type="text" id="address" name="address" required />

    <label for="grade">Grade</label>
    <input type="text" id="grade" name="grade" required />

    <input type="submit" value="Submit" />
  </form>

  <div class="message">
    <h3><a href="form_handling_servlet">View Submitted Data</a></h3>
    <% if (request.getParameter("error") != null) { %>
    <p class="error-message"><%= request.getParameter("error") %></p>
    <% } %>
    <% if (request.getParameter("success") != null) { %>
    <p class="success-message"><%= request.getParameter("success") %></p>
    <% } %>
  </div>
</div>
</body>
</html>
