package com.heng.blog.entity;

import com.heng.blog.util.CommonUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Article {
    @Id
    @GeneratedValue
    // 主键
    private Long id;
    private String title;
    private String headline;
    private String content;
    @ManyToOne
    private User author;
    private String slug;
    private final LocalDateTime addedAt;
    public Article() {
        this.addedAt = LocalDateTime.now();
    }
    public Long getId() {
        return id;
    }
    // chain 风格的 setter
    public Article setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        this.slug = CommonUtil.toSlug(title);
        return this;
    }

    public String getHeadline() {
        return headline;
    }

    public Article setHeadline(String headline) {
        this.headline = headline;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Article setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Article setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }
}
