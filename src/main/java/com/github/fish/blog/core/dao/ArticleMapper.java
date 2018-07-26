package com.github.fish.blog.core.dao;

import com.github.fish.blog.api.entity.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    List<Article> getArticlePageBySearch(@Param("article") Article article);

    Boolean getArticleByName(@Param("articleName") String articleName);

    List<Article> getArticleList(@Param("article") Article article, @Param("num") int num);

    List<Article> getArticleListByPage();

    Article getArticleById(@Param("id") Long id);
}