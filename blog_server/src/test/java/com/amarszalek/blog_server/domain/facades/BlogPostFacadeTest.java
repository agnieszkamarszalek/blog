package com.amarszalek.blog_server.domain.facades;

import com.amarszalek.blog_server.abstractTestClasses.AbstractBlogPostTest;
import com.amarszalek.blog_server.domain.exceptions.EntityCouldNotBeFoundException;
import com.amarszalek.blog_server.domain.exceptions.EntityNotCreatedException;
import com.amarszalek.blog_server.domain.infrastructure.dtos.BlogPostDto;
import com.amarszalek.blog_server.domain.infrastructure.dtos.BlogPostResponseDto;
import com.amarszalek.blog_server.domain.models.BlogPost;
import com.amarszalek.blog_server.domain.repositories.BlogPostRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class BlogPostFacadeTest extends AbstractBlogPostTest {
    @Autowired
    private BlogPostFacade blogPostFacade;
    @MockBean
    private BlogPostRepository blogPostRepository;
    private BlogPost blogPost;
    private BlogPostResponseDto expectedBlogPostResponseDto;
    private BlogPostDto blogPostDto;

    @Before
    public void setUp(){
        this.blogPost = super.sampleBlogPost();
        this.expectedBlogPostResponseDto = super.sampleBlogPostResponseDto();
        this.blogPostDto = super.sampleBlogPostDto();
    }

    @Test
    public void shouldReturnExpectedBlogPostResponseDtoWhenSavingBlogPost(){
        //given
        when(blogPostRepository.save(any(BlogPost.class)))
                .thenReturn(blogPost);
        //when
        BlogPostResponseDto blogPostDtoSaved = blogPostFacade.saveBlogPost(this.blogPostDto);
        //then
        Assert.assertEquals(expectedBlogPostResponseDto, blogPostDtoSaved);
    }

    @Test(expected = EntityNotCreatedException.class)
    public void shouldThrowEntityNotCreatedExceptionWhenSavingBlogPost() {
        //given
        doThrow(new NullPointerException("Entity not created"))
                .when(blogPostRepository)
                .save(any(BlogPost.class));
        //when
        blogPostFacade.saveBlogPost(this.blogPostDto);
    }

    @Test
    public void shouldReturnExpectedBlogPostDtoWhenGettingBlogPost(){
        //given
        when(blogPostRepository.findById(any(Long.class)))
                .thenReturn(java.util.Optional.ofNullable(blogPost));
        //when
        BlogPostResponseDto blogPostDtoFounded = blogPostFacade.getBlogPostById(1L);
        //then
        Assert.assertEquals(expectedBlogPostResponseDto, blogPostDtoFounded);
    }

    @Test(expected = EntityCouldNotBeFoundException.class)
    public void shouldThrowEntityCouldNotBeFoundExceptionWhenGettingBlogPost() {
        //given
        doThrow(new EntityCouldNotBeFoundException("Entity not created"))
                .when(blogPostRepository)
                .findById(any(Long.class));
        //when
        blogPostFacade.getBlogPostById(1L);
    }
}