package com.amarszalek.notifications_server.domain.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;


public class TransportWrapper {

    public void senMessage(Message message) throws MessagingException {
        Transport.send(message);
    }
}
