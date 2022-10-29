package Services;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Jázsi
 */
public class SendEmail {

    private final String MY_EMAIL = "jaskaranDam@gmail.com";
    private final String PASSWORD = "dnauinxarxdejhlh";
    private String emailTo;
    private final String SUBJECT = "Zifar Registration";
    private final String CONTENT = "Hi there!\n"
            + "Thank you for signing up for our Zifar aplications. \n"
            + "Regards,\n"
            + "The Zifar support team";

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public SendEmail() {

        mProperties = new Properties();
    }

    /**
     * Método que crea el email
     *
     * @param email String
     */
    public void createEmail(String email) {
        this.emailTo = email;

        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", MY_EMAIL);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(MY_EMAIL));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(SUBJECT);
            mCorreo.setText(CONTENT, "ISO-8859-1", "html");

        } catch (AddressException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que envia el email
     */
    public void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(MY_EMAIL, PASSWORD);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

        } catch (NoSuchProviderException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
