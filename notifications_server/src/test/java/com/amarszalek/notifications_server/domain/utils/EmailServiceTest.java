package com.amarszalek.notifications_server.domain.utils;

import com.amarszalek.notifications_server.NotificationsServerApplication;
import org.junit.Assert;
import org.junit.Before;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.doNothing;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NotificationsServerApplication.class)
@RunWith(SpringRunner.class)
public class EmailServiceTest extends PowerMockTestCase {
    @MockBean
    private TransportWrapper transportWrapper;

    @Autowired
    private EmailService emailService;


    @Before
    public void setUp() {

    }

    @Test
    public void shouldInvokeTransportWrapperSendMessage() throws MessagingException {
        //given
        String message = "example message";
        String emailAddress = "emailAddress@gmail.com";
        doNothing().when(transportWrapper).sendMessage(isA(Message.class));
        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        //when
        emailService.sendMail(message, emailAddress);
        //then
        verify(transportWrapper, times(1)).sendMessage(messageArgumentCaptor.capture());
        List<Message> allValues = messageArgumentCaptor.getAllValues();
        Assert.assertEquals(message, message);
    }
}