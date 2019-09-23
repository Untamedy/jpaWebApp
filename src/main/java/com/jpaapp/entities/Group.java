/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpaapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "mydb.groups")
@NamedQuery(query = "SELECT g FROM Group g WHERE g.code=:code",name = "find group by code")
public class Group implements Serializable{

    public Group() {
    }

    public Group(String code) {
        this.code = code;        
    }

    public Group(int id, String code) {
        this.id = id;
        this.code = code;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String code;
    
   @OneToMany(mappedBy = "group",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   private List<Student> students = new ArrayList<>();
   

    public int getId() {
        return id;
    }

   
   
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }    

   

    @Override
    public String toString() {
        return "Group{" + "id=" + id + ", code=" + code + '}';
    }
     
    
    
}
