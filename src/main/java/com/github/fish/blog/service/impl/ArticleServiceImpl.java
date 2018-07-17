package com.github.fish.blog.service.impl;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.blog.api.service.ArticleService;
import com.github.fish.blog.core.biz.ArticleBiz;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;



@Component("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleBiz articleBiz;

    @Override
    public Article getArticleById(Long id) {
        return articleBiz.getArticleById(id);
    }

    @Override
    public PageInfo<Article> getArticlePageBySearch(Article article, Pageable pageable) {
        return articleBiz.getArticlePageBySearch(article, pageable);
    }

    @Override
    public Long addArticle(Article article) throws BaseBizException {
        return articleBiz.addArticle(article);
    }

    @Override
    public int updateArticle(Article article) throws BaseBizException {
        return articleBiz.updateArticle(article);
    }

    @Override
    public int deleteArticleById(Long id) throws BaseBizException {
        return articleBiz.deleteArticleById(id);
    }
}
