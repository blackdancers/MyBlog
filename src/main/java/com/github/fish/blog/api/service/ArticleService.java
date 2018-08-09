package com.github.fish.blog.api.service;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface ArticleService {

    public Article getArticleById(Long id);

    /**
     * markdown格式内容转成html格式
     *
     * @param id
     * @return
     */
    public Article getArticleContentById(Long id);


    public PageInfo<Article> getArticlePageBySearch(Article article, Pageable pageable);

    /**
     * 推荐等文章查询
     *
     * @param article
     * @param num
     * @return
     */
    public List<Article> getArticleList(Article article, int num);

    /**
     * 博客首页
     *
     * @param pageable
     * @return
     */
    public PageInfo<Article> getArticleListByPage(Pageable pageable);

    public Long addArticle(Article article) throws BaseBizException;

    public int updateArticle(Article article) throws BaseBizException;

    public int deleteArticleById(Long id) throws BaseBizException;

    /**
     * 分类名称是否重复
     *
     * @param articleName
     * @return
     */
    public Boolean getArticleByName(String articleName);

    PageInfo<Article> getArticlePageByTagId(Article article, Pageable pageable);


    /**
     * 归档数据查询
     */
    Map<String, List<Article>> getArchiveListByYear();

    /**
     * 所有文章
     * @return
     */
    int getArticleCount();
}
