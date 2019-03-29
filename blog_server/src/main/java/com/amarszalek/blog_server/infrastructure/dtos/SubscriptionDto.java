package com.amarszalek.blog_server.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private long id;
    private String userId;
    private String authorUserName;
    private String emailAddress;
}
