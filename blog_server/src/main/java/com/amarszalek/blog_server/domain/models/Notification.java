package com.amarszalek.blog_server.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Notification {
    private String message;
    private String emailAddress;
}
