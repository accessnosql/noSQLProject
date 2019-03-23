/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alu2017440
 */
public class Employee {
    
    //private String id;
    private String name;
    private String pass;
    private String arangoKey;
    private List <Incidence> incidenceList = new ArrayList<>();
    
   
    public Employee( String name, String pass) {
        this.name = name;
        this.pass = pass;   
    }
    
    public Employee(String name) {
    	this.name = name;
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
    
    public String getArangoKey() {
    	return arangoKey; 
    }
    
    public void setArangoKey(String arangoKey) {
    	this.arangoKey = arangoKey;
    }

	@Override
	public String toString() {
		return name + " "  + arangoKey;
	}
    
    
    
    
    

   
    
    
    
    
    
}
