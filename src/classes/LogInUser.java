/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.time.LocalDateTime;

/**
 *
 * @author Shajinder
 */
public class LogInUser {

    private int attempts;

    private LocalDateTime lastLogin;

    public LogInUser() {
        this.attempts = 0;
        this.lastLogin = LocalDateTime.now();
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getAttempts() {
        return attempts;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

}
