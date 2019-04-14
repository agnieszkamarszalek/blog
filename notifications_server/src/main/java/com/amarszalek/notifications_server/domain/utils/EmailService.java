package com.amarszalek.notifications_server.domain.utils;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class EmailService {

    private String host;
    private int port;
    private String password;
    private String username;
    private TransportWrapper transportWrapper;

    public EmailService(String host, int port, String username, String password, TransportWrapper transportWrapper) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.username = username;
        this.transportWrapper = transportWrapper;
    }

    public void sendMail(String messageToSend, String emailAddress) {
        try {
            MessageProviderImpl messageProviderImpl = new MessageProviderImpl(host, port, username, password);
            Message message = messageProviderImpl.getMessage();
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
            message.setSubject("Mail Subject");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(messageToSend, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            transportWrapper.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
