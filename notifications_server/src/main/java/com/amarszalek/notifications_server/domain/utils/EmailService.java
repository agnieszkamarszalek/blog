package com.amarszalek.notifications_server.domain.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailService {

    private String host;
    private int port;
    private String username;
    private String pass;


    public EmailService(String host, int port, String username, String password, String messageToSend, String emailAddress) {

        this.host = host;
        this.port = port;
        this.username = username;
        this.pass = password;

        sendMail(messageToSend, emailAddress);
    }

    private void sendMail(String messageToSend, String emailAddress) {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("setFrom@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
            message.setSubject("Mail Subject");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(messageToSend, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
