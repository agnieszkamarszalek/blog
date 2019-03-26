package com.amarszalek.blog_server.domain.facades;

import com.amarszalek.blog_server.domain.exceptions.EntityCouldNotBeFoundException;
import com.amarszalek.blog_server.domain.exceptions.EntityNotCreatedException;
import com.amarszalek.blog_server.infrastructure.dtos.BlogPostDto;
import com.amarszalek.blog_server.infrastructure.dtos.BlogPostResponseDto;
import com.amarszalek.blog_server.domain.models.BlogPost;
import com.amarszalek.blog_server.domain.repositories.BlogPostRepository;
import com.amarszalek.blog_server.domain.utils.DateProvider;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;

@AllArgsConstructor
public class BlogPostFacade {
    private BlogPostRepository blogPostRepository;
    private ModelMapper modelMapper;
    private DateProvider dateProvider;

    public BlogPostResponseDto saveBlogPost(BlogPostDto blogPostDto){
        BlogPost blogPost = modelMapper.map(blogPostDto, BlogPost.class);
        setDateAndTimeToBlogPost(blogPost);
        try {
            BlogPost blogPostEntity = blogPostRepository.save(blogPost);
            return modelMapper.map(blogPostEntity, BlogPostResponseDto.class);
        } catch (Exception e) {
            throw new EntityNotCreatedException("Blog post not created");
        }
    }

    private void setDateAndTimeToBlogPost(BlogPost blogPost) {
        LocalDateTime currentDateTime = dateProvider.getCurrentDateTime();
        blogPost.setModificationDate(currentDateTime);
    }

    public BlogPostResponseDto getBlogPostById(Long id) {
        BlogPost blogPost = findBlogPostInRepository(id);
        return modelMapper.map(blogPost, BlogPostResponseDto.class);
    }

    private BlogPost findBlogPostInRepository(Long id) {
        return blogPostRepository.findById(id).orElseThrow(
                    () -> new EntityCouldNotBeFoundException("Blog post with id: " + id + " not found"));
    }
}
