/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Manager;
import persistence.IncidenceMethods;

import java.awt.BorderLayout;
import java.io.BufferedReader;

/**
 *
 * @author isain
 */
public class NoSQLProject {
    
      private static BufferedReader br;
      private static Manager manager;
   
    public static void main(String[] args) {
        
	
        manager = Manager.getInstance();
        int option;
        do{
            showMenu();
            option = InputMethods.askInt("Select an option:");
            
            switch(option){
                case 1:
                    newEmployee();
                    break;
                case 2:
                    loginEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    //deleteEmployee();
                    break;
                case 5:
                    //getIncidenceById();
                    break;
                case 6:
                    //incidencesList();
                    break;
                case 7:
                    IncidenceMethods.newIncidence(dbName, arangoDB);;
                    break;
                case 8:
                    //incidenceEmpDest();
                    break;
                case 9:
                    //incidenceEmpOrig();
                    break;
                case 10:
                    //extras();
                	break;
                case 11:
                	manager.createDatabase();
                	break;
                case 12:
                	manager.createCollection("employees");
                	break;
                case 13:
                	manager.createCollection("incidences");
                	break;
                case 0:
                    System.out.println("Leaving application");
                    break;
                default:
                    System.out.println("Option not allowed");
                      
            }
        }while(option != 0);
    }
    
    
    public static void loginEmployee() {
        System.out.println("Login employee:");
        String name = InputMethods.askString("Insert your name:");
        String pass = InputMethods.askString("Insert your password:");
        manager.doEmployeeLogin(name, pass);
    }
    
    public static void newEmployee(){
        System.out.println("New Employee:");
        String name = InputMethods.askString("Insert employee name:");
        String pass = InputMethods.askString("Insert password:");
        manager.insertEmployee(name, pass);
        
    }
    
    public static void updateEmployee() {
        System.out.println("Update Employee:");
        String newName = manager.getEmployeeName();
        String newPass = manager.getEmployeePass();
        boolean changes = false;
        int option = -1;
        do{
            System.out.println("1. Actual name :" + manager.getEmployeeName());
            System.out.println("2. Actual pass :" + manager.getEmployeePass());
            System.out.println("3. Save changes");
            System.out.println("0. Exit without changes.");
            option = InputMethods.askInt("Select an option");
            if(option < 0 || option > 3){
                System.out.println("Wrong option");
            }
            else{
                switch(option){
                    case 1: newName = modifyName(); changes = true; break;
                    case 2: newPass = modifyPass(manager.getEmployeePass()); changes = true; break;
                    case 3: 
                        if(changes) saveChanges(newName, newPass);
                        else System.out.println("Change something first...");
                }
            }
        }while(option != 0 || option != 3);
    }
    
    public static String modifyName(){
        return InputMethods.askString("Enter new name:");
    }
    
    public static String modifyPass(String pass){
        
        return null;
    }
    
    public static void saveChanges(String newName, String newPass){
        manager.updateEmployee(newName, newPass);
    }
    
      public static void showMenu(){
        
        System.out.println("\n*** Employees management STUCOM ***");
        System.out.println("1. Insert new employee");
        System.out.println("2. Login for an employee");
        System.out.println("3. Update an employee");
        System.out.println("4. Delete an employee");
        System.out.println("5. Get incidence by id");
        System.out.println("6. Get incidences list");
        System.out.println("7. Insert a new incidence");
        System.out.println("8. Get incidence from an employee destiny");
        System.out.println("9. Get incidence from an eployee origin");
        System.out.println("10. Extras");
        System.out.println("11. Create database named 'organization'");
        System.out.println("12. Create collection 'employees'");
        System.out.println("13. Create collection 'incidences'");
        System.out.println("0. Exit");
    }
    }
    
    

