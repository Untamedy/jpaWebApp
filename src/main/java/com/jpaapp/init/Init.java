package com.jpaapp.init;

import com.jpaapp.entities.Group;
import com.jpaapp.entities.Student;
import com.jpaapp.services.GroupService;
import com.jpaapp.services.StudentService;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class Init {

    private String stPath = "src\\main\\resources\\students.txt";
    private String grPTH = "src\\main\\resources\\groups.txt";
    private StudentService studentService;
    private GroupService groupService;

    public Init(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    public String parsCSV(String path) {
        StringBuilder objects = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path));) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                objects.append(line);
                objects.append(";");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects.toString();
    }

    public String[] splitData(String data) {
        return data.split(";");
    }

    public void createStudent() {
        List<Student> students = new ArrayList<>();
        String data = parsCSV(stPath);
        String[] studentsData = splitData(data);
        for (String s : studentsData) {
            String[] stData = s.split(",");
            studentService.addStudent(stData[0], stData[1], Integer.valueOf(stData[2]));
        }
        addStudentToGroup();
    }

    public void createGroup() {
        String data = parsCSV(grPTH);
        String[] groupData = splitData(data);
        for (String s : groupData) {
            groupService.addGroup(s);
        }
    }
    
    private void addStudentToGroup(){
        List<Student> allStudents = studentService.selectAll();
        
        for(Student s: allStudents){
            if(s.getId()<=5){
                studentService.setGroupToStudent(s.getLastname(), s.getName(), "qq_1");
            }
            if(s.getId()>5&&s.getId()<=10){
                studentService.setGroupToStudent(s.getLastname(), s.getName(), "tt_2"); 
            }
            if(s.getId()>10&&s.getId()<=15){
                studentService.setGroupToStudent(s.getLastname(), s.getName(), "zz_2");
            }
            if(s.getId()>15){
                 studentService.setGroupToStudent(s.getLastname(), s.getName(), "ht_4");
            }
            
        }
    }

}
