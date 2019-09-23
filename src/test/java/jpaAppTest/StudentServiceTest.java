/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaAppTest;

import com.jpaapp.entities.Student;
import com.jpaapp.init.Init;
import com.jpaapp.services.GroupService;
import com.jpaapp.services.StudentService;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lenovo
 */
public class StudentServiceTest {

    private static EntityManagerFactory entityManagerFactory;
    private static StudentService studentService;
    private static Init init;       
    private static GroupService groupService;

    @BeforeClass
    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("JpaAppMySql");
        studentService = new StudentService(entityManagerFactory);
        groupService = new GroupService(entityManagerFactory);
        init = new Init(studentService,groupService);        
        init.createGroup();
        init.createStudent();        
    }

    @Test
    public void addStudenttest() {
        studentService.addStudent("Jack", "Test", 25);
        studentService.addStudent("Jill", "Test2", 21);
        List<Student> studentList = studentService.findByLastname("Test");
        assertNotNull(studentList.get(0).equals("Test"));

    }

    @Test
    public void updateTest() {
        studentService.setGroupToStudent("Murphy", "Carl", "mm_3");        
        List<Student> st = studentService.findByLastname("Murphy");
        Student s = st.get(0);
        String code = s.getGroup().getCode();
        assertTrue(code.equals("mm_3"));

    }

    @Test
    public void findByAge() {
        List<Student> st = studentService.findByAge(23, 25);
        assertTrue(st.size() == 1);
    }

    @Test
    public void findByLastnameTest() {
        List<Student> st = studentService.findByLastname("Ross");
        assertTrue(st.get(0).getLastname().equals("Ross"));
    }

    @Test
    public void findByGroupTest() {
        List<Student> st = studentService.findByGroup("zz_2");
        String groupCode = st.get(0).getGroup().getCode();
        assertTrue(groupCode.equals("zz_2"));
    }

    @Test
    public void deleteTest() {
        studentService.deleteStudent("Chester", "Lisa", "qq_1");
        assertTrue(studentService.findByLastname("Chester").isEmpty());
    }

}
