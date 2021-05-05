package com.heng.blog.controller;

import antlr.StringUtils;
import com.heng.blog.entity.Article;
import com.heng.blog.repository.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public Iterable<Article> findAll() {
        return articleRepository.findAllByOrderByAddedAtDesc();
    }

    @GetMapping("/{slug}")
    public Article findOne(@PathVariable String slug) throws HttpStatusCodeException {
        Article result = articleRepository.findArticleBySlug(slug);
        if (slug == null || slug.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exit");
        }
        return result;
    }
}
