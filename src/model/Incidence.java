/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author alu2017440
 */
public class Incidence {
    
    private String id;
    private String createdAt;
    private String name;
    private String comment;
    

    public Incidence(String id, String CreatedAt, String name, String comment) {
        this.id = id;
        this.createdAt = CreatedAt;
        this.name = name;
        this.comment = comment;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		createdAt = createdAt;
	}
    
    
    
    
}
