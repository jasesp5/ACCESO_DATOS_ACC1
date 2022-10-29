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

    private final String email;

    private final String userName;

    private final String password;

    private final int age;

    private final int postCode;

    private final String id;

    private LogInUser logInUser;

    public User(String email, String userName, String password, int age, int postCode, String id, LogInUser logInUser) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.postCode = postCode;
        this.id = id;
        this.logInUser = logInUser;
    }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setLogInUser(LogInUser logInUser) {
        this.logInUser = logInUser;
    }

    public LogInUser getLogInUser() {
        return logInUser;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", userName=" + userName + ", password=" + password + ", age=" + age + ", postCode=" + postCode + ", id=" + id + '}';
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getId() {
        return id;
    }

}
