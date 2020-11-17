package Control;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class NotificationMgr
{
    private static final String host_email = "starsse1gr3@gmail.com";
    private static final String host_pwd = "Sample@pass1";


    public static void sendEmail(String recipient_email, String courseID, int indexNum) {
        Properties p_obj = new Properties();
        p_obj.put("mail.smtp.host", "smtp.gmail.com");
        p_obj.put("mail.smtp.socketFactory.port", "465");
        p_obj.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        p_obj.put("mail.smtp.auth", "true");
        p_obj.put("mail.smtp.port", "465");
        Session session = Session.getInstance(p_obj,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(host_email, host_pwd);
                    }
                });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(host_email));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipient_email));
            msg.setSubject("Waitlist notification");
            msg.setText("You have been registered to " + courseID + " and your registered index number is " + indexNum);

            Transport.send(msg);

            System.out.println("An email has been sent to " + recipient_email + "\n");

        } catch (MessagingException e) {
            System.out.println("Error connecting to the internet!");
        }
    }
}
