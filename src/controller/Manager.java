/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.arangodb.ArangoDBException;

import exceptions.EmployeeException;
import model.Employee;
import model.Incidence;
import persistence.ArangoDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Manager {
    
    private Employee employeeLogged;
    
    private static final Manager instance = new Manager();
    public static Manager getInstance() { return instance; }
    
    ArangoDAO arangoDAO;
    
    public Manager() {
        arangoDAO = new ArangoDAO();
    }
    
    public void insertEmployee(String name, String pass){
        arangoDAO.insertEmployee(new Employee(name, pass));
    }
    
    public String doEmployeeLogin(String name, String pass) throws ArangoDBException, EmployeeException{
        employeeLogged = arangoDAO.loginEmployee(name, pass);
        return "Welcome employee " + employeeLogged.getName();
    }
    
    
    public void updateEmployee(String name, String pass) throws ArangoDBException{
    //we send actual employee data and new employee data
      employeeLogged.setName(name);
      employeeLogged.setPass(pass);
      arangoDAO.updateEmployee(employeeLogged);
    }
    
    
    public boolean existsLogged(){
        return employeeLogged != null ? true : false;
    }
    
    public String getEmployeeName(){
        return employeeLogged.getName();
    }
    
    public String getEmployeePass(){
        return employeeLogged.getPass();
    }
    
    
    
    //methods to test creation of dbs and collections
    public void createDatabase() {
    	arangoDAO.createDatabase();
    }
    
 
    public void createCollection(String c) {
    	arangoDAO.createCollection(c);
    }
    
    
    //Incidence Methods
    public void insertIncidence(String id, String createdAt, String comment, String name){
        arangoDAO.createIncidence(new Incidence(id, createdAt, name, comment));
    }
    
    public ArrayList<Incidence> getIncidencesList() {
    	return arangoDAO.incidencesList();
    }
    
    
    // Utilities
    public String actualTimeString() {
    	LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);
        return formatDateTime;
    }
    
    
	
}

