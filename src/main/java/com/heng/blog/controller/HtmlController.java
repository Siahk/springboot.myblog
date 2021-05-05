package com.heng.blog.controller;

import com.heng.blog.domain.RenderedArticle;
import com.heng.blog.entity.Article;
import com.heng.blog.repository.ArticleRepository;
import com.heng.blog.util.CommonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HtmlController {
    private final ArticleRepository repository;
    public HtmlController(ArticleRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/")
    public String blog(Model model) {
        model.addAttribute("title", "Hello, World");
        model.addAttribute("articles", StreamSupport
                .stream(repository.findAllByOrderByAddedAtDesc().spliterator(), true)
                .map(this::render)
                .collect(Collectors.toList()));
        return "blog";
    }

    @GetMapping("/article/{slug}")
    public String article(@PathVariable String slug, Model model) {
        Article article = repository.findArticleBySlug(slug);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist");
        }
        RenderedArticle renderedArticle = render(article);
        model.addAttribute("title", renderedArticle.getTitle());
        model.addAttribute("article", renderedArticle);
        return "article";
    }

    private RenderedArticle render(Article article) {
        return new RenderedArticle().setTitle(article.getTitle())
                .setHeadline(article.getHeadline())
                .setSlug(article.getSlug())
                .setContent(article.getContent())
                .setAuthor(article.getAuthor())
                .setAddedAt(CommonUtil.format(article.getAddedAt()));
    }
}