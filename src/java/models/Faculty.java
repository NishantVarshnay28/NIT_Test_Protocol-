/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Dell
 */
public class Faculty {
    private int tId;
    private String name;
    private int id;
    private String department;
    private String emailId;
    private String password;
    

    public Faculty(String name, int id, String department, String emailId, String password) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.emailId = emailId;
        this.password = password;
    }

    public Faculty() {
    }

    public Faculty(int tId, String name, int id, String department, String emailId, String password) {
        this.tId = tId;
        this.name = name;
        this.id = id;
        this.department = department;
        this.emailId = emailId;
        this.password = password;
    }

    public int gettId() {
        return tId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
