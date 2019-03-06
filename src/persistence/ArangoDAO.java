/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import com.arangodb.ArangoDB;
import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;

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
import model.Employee;

/**
 *
 * @author alu2017454
 */
public class ArangoDAO {

    Configuration configuration;
    ArangoDB arangoDB;

    public ArangoDAO() {
        configuration = new Configuration();
        connect();
    }

    public void createdatabase() {
        String dbName = "dbempleados";
        try {
            arangoDB.createDatabase(dbName);
            System.out.println("Database created: " + dbName);
        } catch (ArangoDBException e) {
            System.err.println("Failed to create database: " + dbName + "; " + e.getMessage());
        }
    }

    private void crearcolecciones() {

    }

    public void updateEmpleado() {
    /*    myObject.addAttribute("c", "Bar");
        try {
            arangoDB.db(dbName).collection(collectionName).updateDocument("myKey", myObject);
        } catch (ArangoDBException e) {
            System.err.println("Failed to update document. " + e.getMessage());
        }*/
    }

    public void insertEmployee(Employee e) {
        BaseDocument myObject = new BaseDocument();
        myObject.addAttribute("username", e.getName());
        myObject.addAttribute("pass", e.getPass());
        try {
            arangoDB.db("dbempleados").collection("Empleado").insertDocument(myObject);
            System.out.println("Document created");
        } catch (ArangoDBException ex) {
            System.err.println("Failed to create document. " + ex.getMessage());
        }
    }

    public void updateEmpleado(Employee e) {

    }

    public void removeEmpleado(Employee e) {

    }

    public void connect() {
        arangoDB = new ArangoDB.Builder().host(configuration.getIp(), configuration.getPort())
                .user(configuration.getUser())
                .password(configuration.getPass())
                .registerModule(new VPackJdk8Module()).build();
    }

    //Falta disconnect no se si es necesario.
    //Falta crear database y colecciones si no existen, como saber si están?
}
