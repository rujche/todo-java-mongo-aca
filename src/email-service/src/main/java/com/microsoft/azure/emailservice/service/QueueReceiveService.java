// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.azure.emailservice.service;

import com.microsoft.azure.common.DueTodoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class QueueReceiveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueReceiveService.class);
    private static final String QUEUE_NAME = "dueTodo";
    private final EmailService emailService;
    @Value("${email.receiver.address}")
    private String emailReceiverEmail;

    public QueueReceiveService(EmailService emailService) {
        this.emailService = emailService;
    }

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(DueTodoMessage dueTodo) {
        this.emailService.sendEmail(dueTodo.userEmail() == null ? emailReceiverEmail : dueTodo.userEmail(),
                                    "Todo item [{0}] is due".formatted(dueTodo.todoName()),
                              null,
                           """
                                    Todo item is due
                                    """);

    }

}
