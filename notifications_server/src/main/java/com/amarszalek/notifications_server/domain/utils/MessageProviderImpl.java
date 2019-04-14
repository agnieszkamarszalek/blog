package com.amarszalek.notifications_server.domain.utils;

import com.amarszalek.notifications_server.domain.repositories.MessageProvider;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MessageProviderImpl implements MessageProvider {
    private Session session;

    public MessageProviderImpl(String host, int port, String username, String password) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", host);
        this.session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
    @Override
    public Message getMessage() {
        return new MimeMessage(this.session);
    }
}
