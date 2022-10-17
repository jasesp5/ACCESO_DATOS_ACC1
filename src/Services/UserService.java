/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import classes.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shajinder
 */
public class UserService {

    private Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public void createUsers(String email, String userName, String password, int age, int postCode, String id) {
        User user = new User(email, userName, password, age, postCode, id);
        users.put(id, user);
    }

}
