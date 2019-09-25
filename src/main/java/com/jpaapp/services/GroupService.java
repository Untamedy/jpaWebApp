/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpaapp.services;

import com.jpaapp.entities.Group;
import com.jpaapp.init.FactoryCreater;
import com.jpaapp.repositary.GroupeRepositary;
import com.jpaapp.repositary.StudentRepositary;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lenovo
 */
public class GroupService {

    public static final Logger LOGGER = Logger.getLogger(GroupService.class.getName());
    
    private EntityManagerFactory managerFactory;
    private FactoryCreater creater;
    private StudentRepositary studentService;
    private GroupeRepositary groupeRepositary;

    

    public GroupService() {
        this.creater = new FactoryCreater();
        this.managerFactory = creater.getEntittyManagerFactoty();
        this.studentService = new StudentRepositary(managerFactory);
        this.groupeRepositary = new GroupeRepositary(managerFactory);
    }

    public void addGroup(String code) {
        Group group = groupeRepositary.findByCode(code);
        if (group == null) {
            Group newGroup = new Group(code);
            groupeRepositary.addGroup(newGroup);
        }
    }

    public void updateGroup(String oldCode, String newCode) {
        Group group = groupeRepositary.findByCode(oldCode);
        if (group != null) {
            groupeRepositary.updateGroup(group, newCode);
        }
    }

    public void deleteGroup(String groupCode) {
        Group group = groupeRepositary.findByCode(groupCode);
        if (group != null) {
            groupeRepositary.deleteGroup(group);
        }
    }

    public Group findByCode(String groupCode) {  
        Group group = groupeRepositary.findByCode(groupCode); 
        if(group==null){
            LOGGER.info("Group with code " + groupCode + " not found");
        }
        return group ;
    }

    public List<Group> selectAll() {
        List<Group> groups = groupeRepositary.selectAll();
        return groups;
    }

}
