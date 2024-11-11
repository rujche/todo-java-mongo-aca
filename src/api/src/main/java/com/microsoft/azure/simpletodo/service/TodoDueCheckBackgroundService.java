package com.microsoft.azure.simpletodo.service;

import com.microsoft.azure.common.DueTodoMessage;
import com.microsoft.azure.simpletodo.model.TodoItem;
import com.microsoft.azure.simpletodo.repository.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class TodoDueCheckBackgroundService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoDueCheckBackgroundService.class);
    private static final String QUEUE_NAME = "dueTodo";

    private final JmsTemplate jmsTemplate;
    private final TodoItemRepository todoItemRepository;

    public TodoDueCheckBackgroundService(JmsTemplate jmsTemplate,
                                         TodoItemRepository todoItemRepository) {
        this.jmsTemplate = jmsTemplate;
        this.todoItemRepository = todoItemRepository;
    }

    @Scheduled(initialDelay = 0, fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public void findDueTasks() {
        LOGGER.info("Running the background service tasks...");
        OffsetDateTime now = OffsetDateTime.now();
        List<TodoItem> todoItems = todoItemRepository.findAll();
        for (TodoItem todoItem : todoItems) {
            if (todoItem.getDueDate() != null
                    && (todoItem.getDueDate().isEqual(now)
                        || todoItem.getDueDate().isAfter(now)
                        || todoItem.getDueDate().isBefore(now.plusDays(1))
                        )
            ) {
                LOGGER.info("Found todo item [{}] to notify", todoItem.getName());
                jmsTemplate.convertAndSend(QUEUE_NAME, new DueTodoMessage(
                        null,
                        todoItem.getName(),
                        todoItem.getDescription(),
                        todoItem.getDueDate()));
            }
        }
    }
}
