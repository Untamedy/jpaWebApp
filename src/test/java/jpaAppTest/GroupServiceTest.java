/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaAppTest;

import com.jpaapp.entities.Group;
import com.jpaapp.init.Init;
import com.jpaapp.services.GroupService;
import com.jpaapp.services.StudentService;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lenovo
 */
public class GroupServiceTest extends Assert{
    
    private static EntityManagerFactory entityManagerFactory;
    private static GroupService groupService;
    private static StudentService studentService;
    private static Init init;        
    
    @BeforeClass
    public static void init(){
    entityManagerFactory = Persistence.createEntityManagerFactory("JpaAppMySql");
    groupService = new GroupService(entityManagerFactory);
    studentService = new StudentService(entityManagerFactory);
    init = new Init(studentService,groupService);
     init.createGroup();  
    }
    
    @Test
    public void addGroupTest(){
    groupService.addGroup("fr_5");
        assertNotNull(groupService.findByCode("fr_5"));
    }
    @Test
    public void updateGrouptest(){
        groupService.updateGroup("fr_5", "de_1");
        assertNotNull(groupService.findByCode("de_1"));
    }
    @Test
    public void deleteGroupTest(){
      Group group =  groupService.findByCode("zz_2");
      if(group!=null){
        groupService.deleteGroup("zz_2");  
      }
      assertNull(groupService.findByCode("zz_2"));      
    }
    @Test
    public void selectAllTest(){
        List<Group> all = groupService.selectAll();
        assertFalse(all.isEmpty());
    }
    @Test
    public void findByCodeTest(){
        Group group = groupService.findByCode("ht_4");
        assertNotNull(group);
    }
    
    
}
