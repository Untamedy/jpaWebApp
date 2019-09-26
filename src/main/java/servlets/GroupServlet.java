/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.jpaapp.entities.Student;
import com.jpaapp.services.StudentService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
@WebServlet("/group")
public class GroupServlet extends HttpServlet {
    private StudentService studentService = new StudentService();
    
    @Override    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String code = req.getParameter("code");
            List<Student> students = studentService.findByGroup(code);
            req.setAttribute("code", code);
            req.setAttribute("list", students);
            RequestDispatcher dispatcher = req.getRequestDispatcher("students.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException ex) {
            Logger.getLogger(GroupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        }
        
        
    
    
}
