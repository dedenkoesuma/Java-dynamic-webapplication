package org.example.dynamicweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final String USER_ID = "admin";
    private static final String PASSWORD = "admin";

    public static class Student {
        public String studentID;
        public String studentName;
        public String department;
        public int mark;

        public Student(String studentID, String studentName, String department, int mark) {
            this.studentID = studentID;
            this.studentName = studentName;
            this.department = department;
            this.mark = mark;
        }

        public String getStudentID() {
            return studentID;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getDepartment() {
            return department;
        }

        public int getMark() {
            return mark;
        }
    }

    public static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student("S1", "Student 1", "Dep 1", 35));
        students.add(new Student("S2", "Student 2", "Dep 1", 70));
        students.add(new Student("S3", "Student 3", "Dep 1", 60));
        students.add(new Student("S4", "Student 4", "Dep 1", 90));
        students.add(new Student("S5", "Student 5", "Dep 2", 30));
        students.add(new Student("S6", "Student 6", "Dep 3", 32));
        students.add(new Student("S7", "Student 7", "Dep 3", 70));
        students.add(new Student("S8", "Student 8", "Dep 3", 20));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        if (USER_ID.equals(userId) && PASSWORD.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("students", students);
            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("index.jsp?error=true");
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "message" + "</h1>");
        out.println("</body></html>");
    }
}
