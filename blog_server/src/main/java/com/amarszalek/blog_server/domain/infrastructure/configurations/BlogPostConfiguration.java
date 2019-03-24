package com.amarszalek.blog_server.domain.infrastructure.configurations;

import com.amarszalek.blog_server.domain.facades.BlogPostFacade;
import com.amarszalek.blog_server.domain.repositories.BlogPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogPostConfiguration {

    @Bean
    BlogPostFacade blogPostFacade(
            BlogPostRepository blogPostRepository,
            ModelMapper modelMapper) {
        return new BlogPostFacade(blogPostRepository, modelMapper);
    }
}
