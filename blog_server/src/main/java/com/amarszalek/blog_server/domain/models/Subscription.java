package com.amarszalek.blog_server.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
public class Subscription {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @NotEmpty(message = "user id can't be empty")
    private String userId;
    @NotEmpty(message = "author user name can't be empty")
    private String authorUserName;
}
