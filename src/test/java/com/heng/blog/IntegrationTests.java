package com.heng.blog;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {BlogApplicationTests.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Test
    void assertBlogPageTitle_Content_And_StatusCode() {
        // 访问路径“/”，以String类型来解析响应的主体entity.body
        ResponseEntity<String> entity = restTemplate.getForEntity("/", String.class);
        // 判断响应的状态码为HttpStatus.OK，即200
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        // 判断entity.body包含“<h1>Hello, World</h1>”
        assertThat(entity.getBody()).contains("<h1>World, Hello</h1>");
    }
    @Test
    void assertArticlePageTitle_Content_And_StatusCode() {
        System.out.println(">> TODO");
    }
    @AfterAll
    void teardown() {
        System.out.println(">> Tear down");
    }
}
