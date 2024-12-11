![image](https://github.com/user-attachments/assets/de7e2fe7-8924-4093-acf0-faf106d1ce72)

# Student Data Management Servlet Application

A Java Servlet and JSP-based web application for managing student data with functionality to add, update, and delete student records. The application features a responsive and aesthetically pleasing user interface and stores data in an XML document. 

## Features

- **Add Students**: A servlet and JSP page to add student records.
- **Update/Delete Students**: Another servlet and JSP page for updating and deleting student records.
- **View/Search and filter"** : View saved data in tabular format , search and filter details
- **Data Storage**: Student data is stored in an XML document.
- **Responsive UI**: User-friendly and responsive interface using modern web design.
- **Apache Tomcat**: Hosted on the Apache Tomcat server.
- **Development Environment**: Developed using IntelliJ IDEA.

## Technologies Used

- **Backend**: Java Servlets and JSP
- **Frontend**: HTML, CSS, and Javascript (for responsive UI)
- **Data Storage**: XML
- **Server**: Apache Tomcat
- **IDE**: IntelliJ IDEA

## Setup and Installation

### Prerequisites

1. Java Development Kit (JDK) installed
2. Apache Tomcat server installed
3. IntelliJ IDEA (or any preferred IDE)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/student-data-management.git
   ```

2. Open the project in IntelliJ IDEA.

3. Configure the Apache Tomcat server in IntelliJ IDEA:
   - Go to `Run > Edit Configurations`.
   - Add a new `Tomcat Server` configuration.
   - Specify the location of your Tomcat server.

4. Build and deploy the project:
   - Navigate to `Build > Build Artifacts`.
   - Deploy the artifact to the Tomcat server.

5. Start the server and access the application at:
   ```
   http://localhost:8080/student-data-management
   ```

## Application Structure

### Servlets

- **AddStudentServlet**: Handles adding new student records.
- **UpdateDeleteServlet**: Manages updating and deleting existing student records.

### XML Data Storage

The student data is stored in `Students.xml` file with the following structure:

```xml
<students>
    <student>
		<id>1731513644940</id>
		<name>ABC</name>
		<gender>male</gender>
		<dateOfBirth>2002-12-22</dateOfBirth>
		<year>2018</year>
		<address>colombo</address>
		<medium>Sinhala</medium>
		<email>abc@gmail.com</email>
		<phoneNumber>0445455487</phoneNumber>
		<grade>12</grade>
	</student>
</students>
```

## Screenshots

### Add Student
![image](https://github.com/user-attachments/assets/6b0b0047-0982-472d-9d7f-f46f6c96cae0)


### Update/Delete Student
![image](https://github.com/user-attachments/assets/b0d325b1-06c5-4fc6-be60-2778d37f7306)


## Usage

1. Navigate to the home page.
2. Use the "Add Student" page to create new student records.
3. Edit or delete records from the "Manage Students" page.

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request for review.


