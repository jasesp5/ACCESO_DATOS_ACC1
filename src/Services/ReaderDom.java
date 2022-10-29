/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import classes.LogInUser;
import classes.User;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Jázsi
 */
public class ReaderDom {

    private final String RUTA = "./resources/files/users.xml";
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

    private File file;

    public ReaderDom() {
        this.file = new File(RUTA);
    }
    /**
     * @param users
     * Método que lee todos los usuarios
     */
    public void readUsers(Map<String, User> users) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            NodeList usersNodeList = document.getElementsByTagName(USER);

            for (int i = 0; i < usersNodeList.getLength(); i++) {
                NodeList datosUsuarios = usersNodeList.item(i).getChildNodes();
                User user = readUserFromXml(datosUsuarios);
                users.put(user.getEmail(), user);
            }
        } catch (IOException | ParserConfigurationException | SAXException exception) {
            System.out.println("Error:" + exception.getMessage());
        }
    }
    /**
     * Método que comprueba si el fichero existe o no
     *  @return boolean  
     */
    public boolean checkFile() {
        return file.exists();
    }
    /**
     * Métdos que lee el nodo user
     * @param  datosUsuarios NodeList 
     * @return User
     */
    public User readUserFromXml(NodeList datosUsuarios) {
        LogInUser logInUser = new LogInUser();
        String id = "";
        String email = "";
        String password = "";
        String userName = "";
        int age = 0;
        int postCode = 0;
        int attempts = 0;
        LocalDateTime localDateTime = null;
        for (int j = 0; j < datosUsuarios.getLength(); j++) {
            switch (datosUsuarios.item(j).getNodeName()) {
                case ID:
                    id = datosUsuarios.item(j).getTextContent();
                    break;
                case EMAIL:
                    email = datosUsuarios.item(j).getTextContent();
                    break;
                case PASSWORD:
                    password = datosUsuarios.item(j).getTextContent();
                    break;
                case USER_NAME:
                    userName = datosUsuarios.item(j).getTextContent();
                    break;
                case POST_CODE:
                    postCode = Integer.parseInt(datosUsuarios.item(j).getTextContent());
                    break;
                case AGE:
                    age = Integer.parseInt(datosUsuarios.item(j).getTextContent());
                    break;
                case LOG_IN_USER:
                    NodeList nodeListLoginUser = datosUsuarios.item(j).getChildNodes();
                    for (int k = 0; k < nodeListLoginUser.getLength(); k++) {
                        if ((ATTEMPTS).equals(nodeListLoginUser.item(k).getNodeName())) {
                            attempts = Integer.parseInt(nodeListLoginUser.item(k).getTextContent());

                        } else if ((LAST_LOG_IN_USER).equals(nodeListLoginUser.item(k).getNodeName())) {
                            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                            localDateTime = LocalDateTime.parse(nodeListLoginUser.item(k).getTextContent(), formatter);
                        }
                    }
                    break;

            }

            logInUser.setAttempts(attempts);
            logInUser.setLastLogin(localDateTime);

        }

        return new User(email, userName, password, age, postCode, id, logInUser);

    }
    
    

}
