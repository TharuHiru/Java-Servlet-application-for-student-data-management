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
    <input type="radio" id="female" name="gender" value="female"></div>

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

    <label for="phoneNumber">Phone Numbers</label>
    <input type="number" id="phoneNumber" name="phoneNumber" required />

    <label for="grade">Grade</label>
    <input type="text" id="grade" name="grade" required />

    <input type="submit" value="Submit" />
  </form>

</div>
</body>
</html>
