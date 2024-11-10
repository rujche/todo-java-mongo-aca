package com.microsoft.azure.emailservice.service;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final EmailClient emailClient;

    @Value("${email.sender.address}")
    private String senderAddress;

    public EmailService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    public void sendEmail(String to, String subject, String body, String bodyHtml) {
        EmailAddress toAddress = new EmailAddress(to);

        EmailMessage emailMessage = new EmailMessage()
                .setSenderAddress(senderAddress)
                .setToRecipients(toAddress)
                .setSubject(subject)
                .setBodyPlainText(body)
                .setBodyHtml(bodyHtml);

        SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(emailMessage, null);
        PollResponse<EmailSendResult> result = poller.waitForCompletion();
        LOGGER.info("Email sent successfully, result {}.", result.getValue());
    }
}
