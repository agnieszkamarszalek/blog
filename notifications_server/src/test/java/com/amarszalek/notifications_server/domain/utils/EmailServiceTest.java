package com.amarszalek.notifications_server.domain.utils;

import com.amarszalek.notifications_server.NotificationsServerApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.modules.testng.PowerMockTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.List;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.doNothing;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NotificationsServerApplication.class)
@RunWith(SpringRunner.class)
public class EmailServiceTest extends PowerMockTestCase {

    private String message = "example message";
    private String emailAddress = "emailAddress@gmail.com";

    @MockBean
    private TransportWrapper transportWrapper;

    @Autowired
    private EmailService emailService;

    @Test
    public void shouldInvokeTransportWrapperSendMessage() throws MessagingException {
        //given
        doNothing().when(transportWrapper).sendMessage(isA(Message.class));
        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        //when
        emailService.sendMail(message, emailAddress);
        //then
        verify(transportWrapper, times(1)).sendMessage(messageArgumentCaptor.capture());
    }

    @Test
    public void shouldInvokeSendMessageMethodWithExpectedMessage() throws MessagingException {
        //given
        String contentMessage = "";
        doNothing().when(transportWrapper).sendMessage(isA(Message.class));
        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        //when
        emailService.sendMail(message, emailAddress);
        //then
        verify(transportWrapper, times(1)).sendMessage(messageArgumentCaptor.capture());
        List<Message> allValues = messageArgumentCaptor.getAllValues();
        try {
            MimeMultipart content = (MimeMultipart) allValues.get(0).getDataHandler().getContent();
            contentMessage = content.getBodyPart(0).getDataHandler().getContent().toString();
            System.out.println("");
        } catch (IOException e) {
            Assert.fail("Unexpected message");
        }
        Assert.assertEquals(message, contentMessage);
    }
}