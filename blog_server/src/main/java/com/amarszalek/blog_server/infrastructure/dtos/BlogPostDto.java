package com.amarszalek.blog_server.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDto {
    private Long id;
    private String authorName;
    private String authorUserName;
    private String content;
    private String subject;
    private List<String> tags;
}
