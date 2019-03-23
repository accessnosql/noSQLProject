/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import utils.IncidenceLevel;

/**
 *
 * @author alu2017440
 */
public class Incidence {
    
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String id; //keydel que lo está creando
    private LocalDateTime createdAt; //fecha de creacion
    private String comment; //descripcion
    private String employeeDest; //key del usuario destinatario
    private IncidenceLevel level;
    private String arangoKey;
    

    public Incidence() {}
    
    public Incidence(String id, String comment, String employeeDest, IncidenceLevel level) {
        this.id = id;
        this.comment = comment;
        this.employeeDest = employeeDest;
        this.level = level;
        this.createdAt = LocalDateTime.now();  
    }
    
    public Incidence(String id, String comment, String employeeDest, IncidenceLevel level, LocalDateTime createdAt) {
        this.id = id;
        this.comment = comment;
        this.employeeDest = employeeDest;
        this.level = level;
        this.createdAt = createdAt;  
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setCreatedAtFromString(String date) {
		this.createdAt = LocalDateTime.parse(date, formatter);
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getEmployeeDest() {
		return employeeDest;
	}


	public void setEmployeeDest(String employeeDest) {
		this.employeeDest = employeeDest;
	}


	public IncidenceLevel getLevel() {
		return level;
	}


	public void setLevel(IncidenceLevel level) {
		this.level = level;
	}
    
	public String actualTimeString() {
	    return createdAt.format(formatter);
	}
	
	

	public String getArangoKey() {
		return arangoKey;
	}

	public void setArangoKey(String arangoKey) {
		this.arangoKey = arangoKey;
	}

	@Override
	public String toString() {
		return "Incidence [_key =" + arangoKey + "]";
	}
	
	
    
    
    
    
    
    
}
