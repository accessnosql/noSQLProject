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
public class Connection {
    
    
    private String ip = "127.0.0.1";
    private int port = 8529;
    private String user = "root";
    private String pass = "admin";
    
    public Connection(){
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    
    
    
}
