/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.util.MapBuilder;
import com.arangodb.velocypack.VPackSlice;
import com.arangodb.velocypack.exception.VPackException;
import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;
import persistence.ArangoDAO;

public class Manager {
    
    private static final Manager instance = new Manager();
    public static Manager getInstance() { return instance; }
    
    Connection connection = new Connection();
    ArangoDAO arangoDAO;
    
    public Manager() {
        arangoDAO = new ArangoDAO();
        arangoDAO.connect();
    }
    
    public void insertEmployee(String name, String pass){
        
    }

   
	
}

