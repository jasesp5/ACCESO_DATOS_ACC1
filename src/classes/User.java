/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Shajinder
 */
public class User {
    
    private String email;
    
    private String Username;
    
    private String password;
    
    private int age;
    
    private int postCode;
    
    private String id;

    public User(String email, String userName, String password, int age, int postCode, String id) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.postCode = postCode;
        this.id = id;
    }
    
    
    
}
