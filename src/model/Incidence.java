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
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;
    private String name;
    private String comment;
    
    public Incidence() {
    }

    public Incidence(String id, LocalDateTime CreatedAt, LocalDateTime UpdatedAt, String name, String comment) {
        this.id = id;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
        this.name = name;
        this.comment = comment;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDateTime CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(LocalDateTime UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
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
    
    
    
}
