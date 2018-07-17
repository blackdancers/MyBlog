package com.github.fish.blog.api.service;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * Created by liudebin on 2018/7/17.
 */
public interface ArticleService {

    public Article getArticleById(Long id);

    public PageInfo<Article> getArticlePageBySearch(Article article, Pageable pageable);

    public Long addArticle(Article article) throws BaseBizException;

    public int updateArticle(Article article) throws BaseBizException;

    public int deleteArticleById(Long id) throws BaseBizException;



}
