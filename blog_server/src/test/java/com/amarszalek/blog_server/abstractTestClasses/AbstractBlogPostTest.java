package com.amarszalek.blog_server.abstractTestClasses;

import com.amarszalek.blog_server.BlogServerApplication;
import com.amarszalek.blog_server.infrastructure.dtos.BlogPostDto;
import com.amarszalek.blog_server.infrastructure.dtos.BlogPostResponseDto;
import com.amarszalek.blog_server.domain.models.BlogPost;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BlogServerApplication.class)
@RunWith(SpringRunner.class)
public abstract class AbstractBlogPostTest {
    private String authorName = "Jon Don";
    private String authorUserName = "donjon";
    private String content = "new blog post";
    protected static LocalDateTime date;
    private String subject = "about new posts";
    private List<String> tags = Arrays.asList("new post", "blog post");

    @BeforeClass
    public static void setUpDate(){
        LocalDateTime localDateTime = LocalDate.of(2019, 03, 25).atTime(6,30);
        date = localDateTime;
    }

    protected BlogPostDto sampleBlogPostDto(){
        BlogPostDto blogPostDto = new BlogPostDto();
        blogPostDto.setAuthorName(authorName);
        blogPostDto.setAuthorUserName(authorUserName);
        blogPostDto.setContent(content);
        blogPostDto.setSubject(subject);
        blogPostDto.setTags(tags);
        return blogPostDto;
    }

    protected BlogPostResponseDto sampleBlogPostResponseDto(){
        BlogPostResponseDto blogPostResponseDto = new BlogPostResponseDto();
        blogPostResponseDto.setAuthorName(authorName);
        blogPostResponseDto.setAuthorUserName(authorUserName);
        blogPostResponseDto.setContent(content);
        blogPostResponseDto.setModificationDate(date);
        blogPostResponseDto.setSubject(subject);
        blogPostResponseDto.setTags(tags);
        return blogPostResponseDto;
    }


    protected BlogPost sampleBlogPost(){
        BlogPost blogPost = new BlogPost();
        blogPost.setAuthorName(authorName);
        blogPost.setAuthorUserName(authorUserName);
        blogPost.setContent(content);
        blogPost.setModificationDate(date);
        blogPost.setSubject(subject);
        blogPost.setTags(tags);
        return blogPost;
    }
}
