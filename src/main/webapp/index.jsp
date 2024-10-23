<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student information form</title>
</head>
<body>
<h2>Submit Data</h2>
<form action="form_handling_servlet" method="post">
    <label>
        <input type="text" name="data" required />
    </label>
    <input type="submit" value="Submit" />
</form>
<h3><a href="form_handling_servlet">View Submitted Data</a></h3>
<% if (request.getParameter("error") != null) { %>
<p style="color:red;"><%= request.getParameter("error") %></p>
<% } %>
<% if (request.getParameter("success") != null) { %>
<p style="color:green;"><%= request.getParameter("success")
%></p>
<% } %>
</body>
</html>
