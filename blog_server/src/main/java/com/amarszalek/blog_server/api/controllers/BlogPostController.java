package com.amarszalek.blog_server.api.controllers;

import com.amarszalek.blog_server.domain.facades.BlogPostFacade;
import com.amarszalek.blog_server.domain.infrastructure.dtos.BlogPostDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/blogPosts")
public class BlogPostController {
    private BlogPostFacade blogPostFacade;

    @PostMapping(consumes="application/json")
    public ResponseEntity saveBlogPost(@RequestBody BlogPostDto blogPostDto){
        return new ResponseEntity(blogPostFacade.saveBlogPost(blogPostDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBlogPost(@PathVariable Long id){
        return new ResponseEntity(blogPostFacade.getBlogPostById(id), HttpStatus.OK);
    }

}
