package com.amarszalek.blog_server.domain.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostResponseDto {
    private Long id;
    private String authorName;
    private String authorUserName;
    private String content;
    private String subject;
    private List<String> tags;
    private LocalDateTime modificationDate;
}


