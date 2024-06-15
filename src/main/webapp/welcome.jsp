<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, org.example.dynamicweb.LoginServlet" %>
<html>
<head>
    <title>Halaman Selamat Datang</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            text-decoration: none;
        }
        td a{
            text-decoration: none;
            color: black;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
            text-decoration: none;
        }
    </style>
    <script>
        function showStudentName(studentName) {
            alert("Student Name: " + studentName);
        }
    </script>
</head>
<body>
<%
    String userId = (String) session.getAttribute("userId");
    List<LoginServlet.Student> students = (List<LoginServlet.Student>) session.getAttribute("students");

    if (userId == null || students == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    // Sort students by department
    students.sort(Comparator.comparing(LoginServlet.Student::getDepartment));
%>
<h1>Welcome <%= userId %></h1>
<table border="1">
    <tr>
        <th>Departemen</th>
        <th>Student ID</th>
        <th>Nilai</th>
        <th>Persentase Kelulusan</th>
    </tr>
    <%
        Map<String, List<LoginServlet.Student>> deptMap = new LinkedHashMap<>();
        for (LoginServlet.Student student : students) {
            deptMap.computeIfAbsent(student.getDepartment(), k -> new ArrayList<>()).add(student);
        }

        for (Map.Entry<String, List<LoginServlet.Student>> entry : deptMap.entrySet()) {
            String department = entry.getKey();
            List<LoginServlet.Student> deptStudents = entry.getValue();
            int totalStudents = deptStudents.size();
            long passStudents = deptStudents.stream().filter(s -> s.getMark() >= 40).count();
            double passPercentage = ((double) passStudents / totalStudents) * 100;
    %>
    <tr>
        <td rowspan="<%= deptStudents.size() %>"><%= department %></td>
        <td><a href="#" onclick="showStudentName('<%= deptStudents.get(0).getStudentName() %>')"><%= deptStudents.get(0).getStudentID() %></a></td>
        <td><%= deptStudents.get(0).getMark() %></td>
        <td rowspan="<%= deptStudents.size() %>"><%= String.format("%.2f", passPercentage) %></td>
    </tr>
    <%
        for (int i = 1; i < deptStudents.size(); i++) {
            LoginServlet.Student student = deptStudents.get(i);
    %>
    <tr>
        <td><a href="#" onclick="showStudentName('<%= student.getStudentName() %>')"><%= student.getStudentID() %></a></td>
        <td><%= student.getMark() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
