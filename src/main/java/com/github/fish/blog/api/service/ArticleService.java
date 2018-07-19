package com.github.fish.blog.api.service;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;


public interface ArticleService {

    public Article getArticleById(Long id);

    public PageInfo<Article> getArticlePageBySearch(Article article, Pageable pageable);

    public Long addArticle(Article article) throws BaseBizException;

    public int updateArticle(Article article) throws BaseBizException;

    public int deleteArticleById(Long id) throws BaseBizException;

    /**
     * 分类名称是否重复
     * @param articleName
     * @return
     */
    public Boolean getArticleByName(String articleName);
}
