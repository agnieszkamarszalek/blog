package com.amarszalek.blog_server.controllers;

import com.amarszalek.blog_server.abstractTestClasses.AbstractBlogPostTest;
import com.amarszalek.blog_server.domain.exceptions.EntityCouldNotBeFoundException;
import com.amarszalek.blog_server.domain.exceptions.EntityNotCreatedException;
import com.amarszalek.blog_server.domain.facades.BlogPostFacade;
import com.amarszalek.blog_server.infrastructure.dtos.BlogPostDto;
import com.amarszalek.blog_server.infrastructure.dtos.BlogPostResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class BlogPostControllerTest extends AbstractBlogPostTest {
    @MockBean
    BlogPostFacade blogPostFacade;
    @Autowired
    TestRestTemplate testRestTemplate;
    private BlogPostDto blogPostDto;
    @LocalServerPort
    private int serverPort;
    private String url;
    private BlogPostResponseDto blogPostResponseDto;

    @Before
    public void setUp(){
        url = "http://localhost:" + serverPort + "/blogPosts";
        blogPostResponseDto = super.sampleBlogPostResponseDto();
        blogPostDto = super.sampleBlogPostDto();
    }

    @Test
    public void shouldReturnStatusCreatedWhenSavingBlogPost(){
        //given
        when(blogPostFacade.saveBlogPost(any(BlogPostDto.class)))
                .thenReturn(blogPostResponseDto);
        //when
        ResponseEntity<BlogPostResponseDto> response = testRestTemplate.postForEntity(url, blogPostDto, BlogPostResponseDto.class);
        //then
        HttpStatus statusCode = response.getStatusCode();
        Assert.assertEquals(HttpStatus.CREATED, statusCode);
    }

    @Test
    public void shouldReturnStatusBadRequestWhenSavingBlogPost() {
        //given
        doThrow(new EntityNotCreatedException("Entity not created"))
                .when(blogPostFacade)
                .saveBlogPost(any(BlogPostDto.class));
        //when
        ResponseEntity<BlogPostResponseDto> response = testRestTemplate.postForEntity(url, blogPostDto, BlogPostResponseDto.class);
        //then
        HttpStatus expectedStatusCode = response.getStatusCode();
        Assert.assertEquals(HttpStatus.BAD_REQUEST, expectedStatusCode);
    }

    @Test
    public void shouldReturnExpectedBlogPostResponseDtoWhenSavingBlogPost() {
        //given
        when(blogPostFacade.saveBlogPost(any(BlogPostDto.class)))
                .thenReturn(blogPostResponseDto);
        //when
        ResponseEntity<BlogPostResponseDto> response = testRestTemplate.postForEntity(url, blogPostDto, BlogPostResponseDto.class);
        //then
        BlogPostResponseDto blogPostResponseDtoSaved = response.getBody();
        Assert.assertEquals(blogPostResponseDto, blogPostResponseDtoSaved);
    }

    @Test
    public void shouldReturnStatusOKWhenGettingBlogPost(){
        //given
        when(blogPostFacade.getBlogPostById(any(Long.class)))
                .thenReturn(blogPostResponseDto);
        //when
        ResponseEntity<BlogPostDto> response = testRestTemplate.getForEntity(url + "/1",BlogPostDto.class);
        //then
        HttpStatus expectedStatusCode = response.getStatusCode();
        Assert.assertEquals(HttpStatus.OK, expectedStatusCode);
    }

    @Test
    public void shouldReturnStatusNotFoundWhenGettingBlogPost() {
        //given
        doThrow(new EntityCouldNotBeFoundException("Entity not found"))
                .when(blogPostFacade)
                .getBlogPostById(any(Long.class));
        //when
        ResponseEntity<BlogPostDto> response = testRestTemplate.getForEntity(url + "/1", BlogPostDto.class);
        //then
        HttpStatus expectedStatusCode = response.getStatusCode();
        Assert.assertEquals(HttpStatus.NOT_FOUND, expectedStatusCode);
    }

    @Test
    public void shouldReturnExpectedBlogPostDtoWhenGettingBlogPost() {
        //given
        when(blogPostFacade.getBlogPostById(any(long.class)))
                .thenReturn(blogPostResponseDto);
        //when
        ResponseEntity<BlogPostResponseDto> response = testRestTemplate.getForEntity(url + "/1", BlogPostResponseDto.class);
        //then
        BlogPostResponseDto blogPostDtoSaved = response.getBody();
        Assert.assertEquals(blogPostResponseDto, blogPostDtoSaved);
    }
}
