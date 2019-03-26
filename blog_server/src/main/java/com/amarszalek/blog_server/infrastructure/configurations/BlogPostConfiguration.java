package com.amarszalek.blog_server.infrastructure.configurations;

import com.amarszalek.blog_server.domain.facades.BlogPostFacade;
import com.amarszalek.blog_server.domain.repositories.BlogPostRepository;
import com.amarszalek.blog_server.domain.utils.DateProvider;
import com.amarszalek.blog_server.domain.utils.DateProviderImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogPostConfiguration {

    @Bean
    BlogPostFacade blogPostFacade(
            BlogPostRepository blogPostRepository,
            ModelMapper modelMapper,
            DateProvider dateProvider) {
        return new BlogPostFacade(blogPostRepository, modelMapper, dateProvider);
    }

    @Bean
    DateProvider dateProvider(){
        return new DateProviderImpl();
    }

}
