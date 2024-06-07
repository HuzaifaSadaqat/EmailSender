package EmailSender;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

public class Email {

    public void sendEmail() {
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;
        String username = "huzaifasadaqat22@gmail.com";
        String password = "aclb wpiy hbro dwji";
        String toAddress = "basharat.hussain@riphah.edu.pk";
        String fromAddress = "huzaifasadaqat22@gmail.com";
        String fromName = "Huzaifa";
        String subject = "Send mail With attachment.. ";
        String content = "<b> HI Zeff here /.Asslamalikm..................</b>";
        String filePath = "src/javax.mail.jar"; // Replace with your actual file path

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress, fromName));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            message.setSubject(subject);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Text part
            BodyPart textPart = new MimeBodyPart();
            textPart.setContent(content, "text/html");
            multipart.addBodyPart(textPart);

            // Attachment part
            BodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("attachment"); // Customize the file name
            multipart.addBodyPart(attachmentPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
