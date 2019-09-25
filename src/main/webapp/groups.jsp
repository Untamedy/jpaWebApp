<%-- 
    Document   : statistic
    Created on : Aug 20, 2019, 8:11:22 PM
    Author     : YBolshakova
--%>


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
            List<Group> groups = (List<Group>) request.getAttribute("list");
            if(!groups.isEmpty()){
                 for (Group g : groups) {
                <button onclick="location.href='/Questionnaire'">Back to main</button>;
            }
            }else{
                out.println("<h1>" + "Sorry, we don't have any information yet" + "</h1>");
            }
           
        %>
        <div>
            <button onclick="location.href='/Questionnaire'">Back to main</button>
        </div>
    </ul>
</html>
