<%-- 
    Document   : statistic
    Created on : Aug 20, 2019, 8:11:22 PM
    Author     : YBolshakova
--%>


<%@page import="com.jpaapp.entities.Student"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@page import="com.jpaapp.entities.Group"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Our Groups</title>
    </head>
    <body>
        <h1>This is the list of our groups:</h1>
    </body>
    <ul>
        <%
            List<Student> students = (List<Student>) request.getAttribute("list");
           out.println("<h1>" +"Group"+ request.getParameter("code")+"</h1>");
            if (!students.isEmpty()) {
                for (Student s : students) {

                    out.println("<h1>" + s.toString() + "</h1>");

                }
            } else {
                out.println("<h1>" + "Sorry, we don't have any information yet" + "</h1>");
            }

        %>
        <div>
            <button onclick="location.href = 'index.jsp'">Back to main</button>
        </div>
    </ul>
</html>
