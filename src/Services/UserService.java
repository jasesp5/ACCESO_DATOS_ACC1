/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import classes.LogInUser;
import classes.User;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Jázsi
 */
public class UserService {

    private WriterDom writerDom;
    private Map<String, User> users;
    private ReaderDom readerDom;

    private final int MAXIUM_ATTEMPTS = 3;

    public UserService() {
        this.users = new HashMap<>();
        this.writerDom = new WriterDom();
        this.readerDom = new ReaderDom();

    }

    /**
     * Método que crea el usuario
     *
     * @param email String
     * @param userName String
     * @param password String
     * @param age int
     * @param postCode int
     * @param id String
     * @param loginUser LogInUser
     */
    public void createUsers(String email, String userName, String password, int age, int postCode, String id, LogInUser loginUser) {
        User user = new User(email, userName, password, age, postCode, id, loginUser);
        users.put(email, user);
    }

    /**
     * Getter de users
     *
     * @return users
     */
    public Map<String, User> getUsers() {
        return users;
    }

    /**
     * Método que llama a leer usuarios de la clase readerDom
     */
    public void readAlltheUser() {
        if (this.readerDom.checkFile()) {
            this.readerDom.readUsers(this.users);
        }

    }

    /**
     * Método que llama writer dom para escribir los usuarios
     */
    public void writeUsers() {
        writerDom.writeUser(this.users);
    }

    /**
     * Método que comprueba el email
     * @param email String
     * @return boolean devuelve true si el email es correcto y false si es incorrecto
     */
    public boolean checkEmail(String email) {
        if (this.users.containsKey(email)) {
            return true;
        }
        return false;
    }

    /**
     * Método que comprueba la contraseña
     *
     * @param email String
     * @param password String
     * @return boolean
     */
    public boolean checkPassword(String email, String password) {
        if (this.users.get(email).getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    /**
     * Método que comprueba el usuario
     *
     * @param username String
     * @return boolean
     */
    public boolean checkUsername(String username) {
        for (User user : this.users.values()) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que comprueba si el formato del email es correcto
     *
     * @param email String
     * @return boolean
     */
    public boolean checkIfTheEmailIsValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    /**
     * Método que comprueba los si los intentos no se pasa de 3
     *
     * @param email String
     * @return boolean
     */
    public boolean checkAttempts(String email) {
        return this.users.get(email).getLogInUser().getAttempts() == MAXIUM_ATTEMPTS;
    }

    /**
     * Método que suma los intentos
     *
     * @param email String
     */
    public void plusTheAttempts(String email) {
        int attempts = this.users.get(email).getLogInUser().getAttempts();
        attempts++;
        this.users.get(email).getLogInUser().setAttempts(attempts);
    }

    /**
     * Método que setea la hora del último login
     *
     * @param email String
     * @param time LocalDateTime
     */
    public void setLastTimeLogIn(String email, LocalDateTime time) {
        this.users.get(email).getLogInUser().setLastLogin(time);
    }

    /**
     * Método que comprueba si han pasado las 24 horas
     *
     * @param email String
     * @param time LocalDateTime
     */
    public void checkLasTimeLoginAndSetTheAttempts(String email, LocalDateTime time) {
        if (time.isAfter(this.users.get(email).getLogInUser().getLastLogin().plusHours(24))) {
            this.users.get(email).getLogInUser().setAttempts(0);
        }

    }
}
