/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Manager;
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
                    //loginEmployee();
                    break;
                case 3:
                    //updateEmployee();
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
                    //newIncidence();
                    break;
                case 8:
                    //incidenceEmpDest();
                    break;
                case 9:
                    //incidenceEmpOrig();
                    break;
                case 10:
                    //extras();
                case 0:
                    System.out.println("Leaving application");
                    break;
                default:
                    System.out.println("Option not allowed");
                      
            }
        }while(option != 0);
    }
    
    public static void newEmployee(){
        System.out.println("New Employee:");
        String name = InputMethods.askString("Insert employee name:");
        String pass = InputMethods.askString("Insert password:");
        manager.insertEmployee(name, pass);
        
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
        System.out.println("0. Exit");
    }
    }
    
    

