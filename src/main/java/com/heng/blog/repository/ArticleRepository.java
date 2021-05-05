package com.heng.blog.repository;

import com.heng.blog.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    Article findArticleBySlug(String slug);
    Iterable<Article> findAllByOrderByAddedAtDesc();
}
