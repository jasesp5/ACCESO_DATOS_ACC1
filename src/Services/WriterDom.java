/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

/**
 *
 * @author Shajinder
 */
public class WriterDom {

    public void leer() {
        DocumentBuilderFactory factory = new DocumentBuilderFactory.newInstance();
        File file = new File("\\resources\\Files\\users.xml");

        try {

            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");

            /**
             * Creación de la fuente XML a partir del documento
             */
            Source source = new DOMSource(document);

            /**
             * Se crea el resultado en el fichero nuevosEmpleados.xml
             */
            Result result = new StreamResult(file);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            /**
             * Se realiza la transformación del documento a fichero
             */
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException | DOMException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
