package org.example.dynamicweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final String USER_ID = "admin";
    private static final String PASSWORD = "admin";

    public static class Student {
        private String studentID;
        private String studentName;
        private String department;
        private int mark;

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

    private static List<Student> students = new ArrayList<>();

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        if (USER_ID.equals(userId) && PASSWORD.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("students", students);
            resp.sendRedirect("welcome.jsp");
        } else {
            resp.sendRedirect("index.jsp?error=true");
        }
    }
}
