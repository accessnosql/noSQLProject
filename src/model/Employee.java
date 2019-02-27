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
    
    private String id;
    private String Name;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;
    private List <Incidence> incidenceList = new ArrayList<>();
    
    public Employee() {
    }

    public Employee(String id, String Name, LocalDateTime CreatedAt, LocalDateTime UpdatedAt) {
        this.id = id;
        this.Name = Name;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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

    public List<Incidence> getIncidenceList() {
        return incidenceList;
    }

    public void setIncidenceList(List<Incidence> incidenceList) {
        this.incidenceList = incidenceList;
    }
    
    
    
    
    
}
