/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import classes.User;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Jázsi
 */
public class WriterDom {

    private final String RUTA = "resources\\Files\\users.xml";
    private final String ID = "id";
    private final String EMAIL = "email";
    private final String PASSWORD = "password";
    private final String USER_NAME = "userName";
    private final String POST_CODE = "postCode";
    private final String AGE = "age";
    private final String ATTEMPTS = "attempts";
    private final String LOG_IN_USER = "loginUser";
    private final String LAST_LOG_IN_USER = "lastLoginTime";
    private final String USER = "user";
    private final String USERS = "Users";
    private final String VERSION_XML = "1.0";
    
    /**
     * Método que escribe el user
     * @param users Users 
     */

    public void writeUser(Map<String, User> users) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        File file = new File(RUTA);

        try {

            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document document = implementation.createDocument(null, USERS, null);
            document.setXmlVersion(VERSION_XML);
            writeAllTheUsersInTheArray(users, document);

            Source source = new DOMSource(document);

            Result result = new StreamResult(file);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException | DOMException exception) {
            System.out.println(exception.getMessage());
        }
    }
    /**
     * Método que crear el usuario
     * @param document Document
     * @param id String
     * @param email String
     * @param userName String
     * @param password String
     * @param postCode Strig
     * @param age String
     * @param atempts String
     * @param time  String 
     */
    public void createUser(Document document, String id, String email, String userName, String password, String postCode, String age, String atempts, String time) {
        Element elementUser = document.createElement(USER);
        document.getDocumentElement().appendChild(elementUser);
        writeId(document, elementUser, id);
        writeEmail(document, elementUser, email);
        writeUserName(document, elementUser, userName);
        writePassword(document, elementUser, password);
        writePostCode(document, elementUser, postCode);
        writeAge(document, elementUser, age);
        writeLoginUser(document, elementUser, atempts, time);

    }
    /**
     * Método que escribe el usuario
     * @param document Document
     * @param elementUser Element
     * @param atempts String
     * @param time String
     */
    public void writeLoginUser(Document document, Element elementUser, String atempts, String time) {
        Element loginUser = document.createElement(LOG_IN_USER);
        elementUser.appendChild(loginUser);
        Element attempts = document.createElement(ATTEMPTS);
        Text textAttempts = document.createTextNode(atempts);
        attempts.appendChild(textAttempts);
        Element lastLoginTime = document.createElement(LAST_LOG_IN_USER);
        Text textLastLogin = document.createTextNode(time);
        lastLoginTime.appendChild(textLastLogin);
        loginUser.appendChild(attempts);
        loginUser.appendChild(lastLoginTime);
    }
    /**
     * Método que escribe el id
     * @param document Document
     * @param elementUser Element
     * @param id String
     */
    public void writeId(Document document, Element elementUser, String id) {
        Element elementId = document.createElement(ID);
        Text textId = document.createTextNode(id);
        elementId.appendChild(textId);
        elementUser.appendChild(elementId);
    }
    /**Método que escribe el userName
     * @param document Document
     * @param elementUser Element
     * @param userName String
     */
    public void writeUserName(Document document, Element elementUser, String userName) {
        Element elementUserName = document.createElement(USER_NAME);
        Text textUsername = document.createTextNode(userName);
        elementUserName.appendChild(textUsername);
        elementUser.appendChild(elementUserName);
    }
    /**
     * Método que escribe el email
     * @param document Document
     * @param elementUser Element
     * @param email String
     */
    public void writeEmail(Document document, Element elementUser, String email) {
        Element elementEmail = document.createElement(EMAIL);
        Text textEmail = document.createTextNode(email);
        elementEmail.appendChild(textEmail);
        elementUser.appendChild(elementEmail);
    }
    /**
     * Método que escribe el código postal 
     * @param document Document
     * @param elementUser Element
     * @param postCode String
     */
    public void writePostCode(Document document, Element elementUser, String postCode) {
        Element elementPostCode = document.createElement(POST_CODE);
        Text textPostCode = document.createTextNode(postCode);
        elementPostCode.appendChild(textPostCode);
        elementUser.appendChild(elementPostCode);
    }
    /**
     * Método que escribe la edad
     * @param document Document
     * @param elementUser Element
     * @param age String
     */
    public void writeAge(Document document, Element elementUser, String age) {
        Element elementAge = document.createElement(AGE);
        Text textAge = document.createTextNode(age);
        elementAge.appendChild(textAge);
        elementUser.appendChild(elementAge);
    }
    /**
     * Método que escribe la contraseña
     * @param document Document
     * @param elementUser Element
     * @param password String
     */
    public void writePassword(Document document, Element elementUser, String password) {
        Element elementPassword = document.createElement(PASSWORD);
        Text textPassword = document.createTextNode(password);
        elementPassword.appendChild(textPassword);
        elementUser.appendChild(elementPassword);
    }
    /**
     * Método que escribe todos los usuarios
     * @param users Users
     * @param document Document
     */
    public void writeAllTheUsersInTheArray(Map<String, User> users, Document document) {
        for (User user : users.values()) {
            String id = user.getId();
            String email = user.getEmail();
            String userName = user.getUserName();
            String password = user.getPassword();
            String postCode = String.valueOf(user.getPostCode());
            String age = String.valueOf(user.getAge());
            String attempts = String.valueOf(user.getLogInUser().getAttempts());
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            String time = user.getLogInUser().getLastLogin().format(formatter);
            createUser(document, id, email, userName, password, postCode, age, attempts, time);
        }
    }

}
