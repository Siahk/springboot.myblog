package com.heng.blog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class SubmitArticleQuery {
    private String title;
    private String headline;
    private String content;
    private String author;
}