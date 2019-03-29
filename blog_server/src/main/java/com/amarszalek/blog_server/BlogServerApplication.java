package com.amarszalek.blog_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories
@EnableAsync
public class BlogServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogServerApplication.class, args);
	}

}
