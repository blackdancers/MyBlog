package com.github.fish.blog.core.dao;

import com.github.fish.blog.api.entity.ArticleRefTag;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleRefTagMapper extends Mapper<ArticleRefTag> {

    String getTagIdsByArticleId(@Param("articleId") Long articleId);

    int deleteTagsByArticleId(Long articleId);

    int getArticleNumByTagId(@Param("tagId") Long tagId);
}