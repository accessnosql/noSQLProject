
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Manager;
import controller.SwingController;
import exceptions.EmployeeException;
import model.Incidence;
import persistence.IncidenceMethods;
import utils.IncidenceLevel;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.arangodb.ArangoDBException;

/**
 *
 * @author isain
 */
public class NoSQLProject {
    
      private static BufferedReader br;
      private static Manager manager;
   
    public static void main(String[] args) {
        
	    //instance of manager of swing
    	SwingController swingController = SwingController.getInstance();
    	//instance of manager of console view
        manager = Manager.getInstance();
        int option = -1;
        do{
           try {
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
                    incidencesList();
                    break;
                case 7:
                    insertIncidence();
                    break;
                case 8:
                	updateIncidence();
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
           }catch(EmployeeException | ArangoDBException e) {
        	   System.out.println(e.getMessage());
           }
        }while(option != 0);
    }
    
    
    public static void loginEmployee() throws ArangoDBException, EmployeeException {
        System.out.println("Login employee:");
        String name = InputMethods.askString("Insert your name:");
        String pass = InputMethods.askString("Insert your password:");
        System.out.println(manager.doEmployeeLogin(name, pass));
    }
    
    public static void newEmployee(){
        System.out.println("New Employee:");
        String name = InputMethods.askString("Insert employee name:");
        String pass = InputMethods.askString("Insert password:");
        manager.insertEmployee(name, pass);
        
    }
    
    public static void incidencesList() {
    	for (Incidence incidence : manager.getIncidencesList()) {
			System.out.println(incidence.getId());
			System.out.println(incidence.getCreatedAt());
			System.out.println(incidence.getComment());
			System.out.println(incidence.getEmployeeDest());
			System.out.println(incidence.getLevel());
		}
    }
    
    
    public static void insertIncidence() {
    	
    	/*
    	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        private String id; //keydel que lo est� creando
        private LocalDateTime createdAt; //fecha de creacion
        private String comment; //descripcion
        private String employeeDest; //key del usuario destinatario
        private IncidenceLevel level;*/
        
        
    	System.out.println("New Inicidence:");
    	String id= InputMethods.askString("Insert affair");
    	String comment = InputMethods.askString("Insert comment");
    	String employeeDest = InputMethods.askString("Insert employeeDest");
    	IncidenceLevel level = IncidenceLevel.HIGH;
    	
    	manager.insertIncidence(id, comment,employeeDest, level);
    	
    }
    /*
     * En la versi�n final este metodo solo se podr� aplicar si hay un employee logueado, si no dar� error
     */
    public static void updateEmployee() throws EmployeeException, ArangoDBException {
        System.out.println("Update Employee : "+ manager.getEmployeeName());
        String name = manager.getEmployeeName();
        String pass = manager.getEmployeePass();
        String newName = "";
        String newPassCheck1 = "";
        boolean changes = false;
   
        if(wantModify("Name : "+ name)) {
        	changes = true;
        	newName = InputMethods.askString("Enter new name");
        }
        if(wantModify("Pass : ****")) {
        	changes = true;
        	String actualPass = InputMethods.askString("Actual pass: ");
        	if(actualPass.equals(pass)){
              newPassCheck1 = InputMethods.askString("Enter new pass: ");
        	  String newPassCheck2 = InputMethods.askString("Enter new pass again: ");
        	  if(!newPassCheck1.contentEquals(newPassCheck2)) {
        		  throw new EmployeeException(EmployeeException.WRONG_PASS_CHECK);
        	  }
        	}else {
        		throw new EmployeeException(EmployeeException.WRONG_PASS);
           }
        }
        if(changes && wantModify("Save changes")) {
             manager.updateEmployee(newName, newPassCheck1);
             System.out.println("Data successfully updated");
        }
       
    }
    
    public static void updateIncidence() {
    	/*System.out.println("Update incidence!!!");
    	
    	String key = InputMethods.askString("write update incidence key?");*/
    	
    	manager.updateIncidence("1235");
    }
    
    
   public static boolean wantModify(String msg) {
	   System.out.println(msg);
	   if("".equals(InputMethods.askStringNullable("Return to modify, any other to pass"))){
		   return true;
	   }
	   return false;
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
    
    

