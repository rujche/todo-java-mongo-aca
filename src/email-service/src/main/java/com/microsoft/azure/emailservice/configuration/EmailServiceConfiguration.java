package com.microsoft.azure.emailservice.configuration;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailServiceConfiguration {

    @Value("${azure.communication-service.connection-string}")
    private String connectionString;

    @Bean
    EmailClient emailClient() {
        return new EmailClientBuilder().connectionString(connectionString).buildClient();
    }
}
