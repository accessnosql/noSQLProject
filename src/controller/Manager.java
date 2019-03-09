/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Employee;
import persistence.ArangoDAO;

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
    
    public void doEmployeeLogin(String name, String pass ){
        //employeeLogged = arangoDAO.doEmployeeLogin(new Employee(name, pass));
    }
    
    public void updateEmployee(String name, String pass){
       // arangoDAO.updateEmployee(new Employee(name, pass));
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

   
	
}

