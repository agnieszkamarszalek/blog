package com.amarszalek.blog_server.domain.repositories;
import com.amarszalek.blog_server.domain.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

}
