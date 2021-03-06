package com.github.fish.blog.service.impl;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.blog.api.service.ArticleService;
import com.github.fish.blog.core.biz.ArticleBiz;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Component("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleBiz articleBiz;

    @Override
    public Article getArticleById(Long id) {
        return articleBiz.getArticleById(id);
    }

    @Override
    public Article getArticleContentById(Long id) {
        return articleBiz.getArticleContentById(id);
    }

    @Override
    public PageInfo<Article> getArticlePageBySearch(Article article, Pageable pageable) {
        return articleBiz.getArticlePageBySearch(article, pageable);
    }

    @Override
    public List<Article> getArticleList(Article article, int num) {
        return articleBiz.getArticleList(article, num);
    }

    @Override
    public PageInfo<Article> getArticleListByPage(Pageable pageable) {
        return articleBiz.getArticleListByPage(pageable);
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

    @Override
    public Boolean getArticleByName(String articleName) {
        return articleBiz.getArticleByName(articleName);
    }

    @Override
    public PageInfo<Article> getArticlePageByTagId(Article article, Pageable pageable) {
        return articleBiz.getArticlePageByTagId(article, pageable);
    }

    @Override
    public Map<String, List<Article>> getArchiveListByYear() {
        return articleBiz.getArchiveListByYear();
    }

    @Override
    public int getArticleCount() {
        return articleBiz.getArticleCount();
    }
}
