package com.amarszalek.blog_server.controllers;

import com.amarszalek.blog_server.abstractTestClasses.AbstractBlogPostTest;
import com.amarszalek.blog_server.infrastructure.dtos.BlogPostDto;
import com.amarszalek.blog_server.infrastructure.dtos.BlogPostResponseDto;
import com.amarszalek.blog_server.domain.models.BlogPost;
import com.amarszalek.blog_server.domain.repositories.BlogPostRepository;
import com.amarszalek.blog_server.domain.utils.DateProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class BlogPostControllerIntegrationTest extends AbstractBlogPostTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    BlogPostRepository blogPostRepository;
    @LocalServerPort
    int serverPort;
    private String url;
    private BlogPostDto blogPostDto;
    private BlogPostResponseDto expectedBlogPostResponseDto;
    @MockBean
    private DateProvider dateProvider;

    @Before
    public void setUp() {
        url = "http://localhost:" + serverPort + "/blogPosts";
        blogPostDto = super.sampleBlogPostDto();
        expectedBlogPostResponseDto = super.sampleBlogPostResponseDto();
        when(dateProvider.getCurrentDateTime()).thenReturn(super.date);
    }

    @Test
    public void shouldSaveBlogPost() {
        //when
        ResponseEntity<BlogPostResponseDto> response = testRestTemplate.postForEntity(url, blogPostDto, BlogPostResponseDto.class);
        //then
        Optional<BlogPost> foundedBlogPostOptional = blogPostRepository.findById(response.getBody().getId());
        Assert.assertTrue(foundedBlogPostOptional.isPresent());
    }

    @Test
    public void shouldGetSavedBlogPost() {
        //given
        ResponseEntity<BlogPostResponseDto> responsePost = testRestTemplate.postForEntity(url, blogPostDto, BlogPostResponseDto.class);
        BlogPostResponseDto blogPostResponseDto = responsePost.getBody();
        Long blogPostSavedId = blogPostResponseDto.getId();
        //when
        ResponseEntity<BlogPostResponseDto> response = testRestTemplate.getForEntity(url + "/" + blogPostSavedId, BlogPostResponseDto.class);
        //then
        BlogPostResponseDto blogPostResponseDtoByGetMethod = response.getBody();
        expectedBlogPostResponseDto.setId(blogPostSavedId);
        Assert.assertEquals(expectedBlogPostResponseDto, blogPostResponseDtoByGetMethod);
    }
}