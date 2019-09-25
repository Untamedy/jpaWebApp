/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.jpaapp.entities.Group;
import com.jpaapp.services.GroupService;
import com.jpaapp.services.StudentService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
@WebServlet("/listGroups")
public class AddGroupServlet extends HttpServlet {
    
    private GroupService groupService = new GroupService();
            

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Group> groups = groupService.selectAll();
        req.setAttribute("list", groups);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/groups");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException ex) {
            Logger.getLogger(AddGroupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

   

}
