package com.microsoft.azure.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.OffsetDateTime;

@JsonSerialize
public record DueTodoMessage(String userEmail,
                             String todoName,
                             String todoDescription,
                             OffsetDateTime todoDueDate) {

}
