package com.microsoft.azure.emailservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfiguration {

    private final JmsListenerContainerFactory jmsListenerContainerFactory;

    public JmsConfiguration(JmsListenerContainerFactory jmsListenerContainerFactory) {
        this.jmsListenerContainerFactory = jmsListenerContainerFactory;
    }

    @PostConstruct
    public void init() {
        if (this.jmsListenerContainerFactory instanceof DefaultJmsListenerContainerFactory) {
            ((DefaultJmsListenerContainerFactory) this.jmsListenerContainerFactory).setMessageConverter(jacksonJmsMessageConverter());
        }
    }

    private MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);

        converter.setObjectMapper(objectMapper());
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

}
