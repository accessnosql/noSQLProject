/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alu2017440
 */
public class Employee {
    
    //private String id;
    private String name;
    private String pass;
   
    private LocalDateTime loggedAt;
    private List <Incidence> incidenceList = new ArrayList<>();
    
   
    public Employee( String name, String pass) {
        //this.id = id;
        this.name = name;
        this.pass = pass;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Incidence> getIncidenceList() {
        return incidenceList;
    }

    public void setIncidenceList(List<Incidence> incidenceList) {
        this.incidenceList = incidenceList;
    }
    
    

   
    
    
    
    
    
}
