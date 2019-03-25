package com.amarszalek.blog_server.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class BlogPost {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String authorName;
    private String authorUserName;
    private String content;
    private LocalDateTime modificationDate;
    private String subject;
    @ElementCollection
    @CollectionTable(name="post_tags", joinColumns=@JoinColumn(name="post_id"))
    private List<String> tags = new ArrayList<>();
}
