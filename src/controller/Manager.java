/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author alu2017440
 */
public class Manager {
    
    private Manager() {
   
	}
     private static final Manager instance = new Manager();
     public static Manager getInstance() { return instance; }
	
}

