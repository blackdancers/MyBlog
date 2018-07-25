package com.github.fish.blog.api.service;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ArticleService {

    public Article getArticleById(Long id);

    public PageInfo<Article> getArticlePageBySearch(Article article, Pageable pageable);

    /**
     * 推荐等文章查询
     * @param article
     * @param pageable
     * @return
     */
    public List<Article> getArticleList(Article article, Pageable pageable);

    /**
     * 博客首页
     * @param pageable
     * @return
     */
    public PageInfo<Article> getArticleListByPage(Pageable pageable);

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
