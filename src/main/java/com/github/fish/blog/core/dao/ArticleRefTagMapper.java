package com.github.fish.blog.core.dao;

import com.github.fish.blog.api.entity.ArticleRefTag;
import com.github.fish.blog.api.entity.Tags;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleRefTagMapper extends Mapper<ArticleRefTag> {

    String getTagIdsByArticleId(@Param("articleId") Long articleId);

    int deleteTagsByArticleId(Long articleId);

    int getArticleNumByTagId(@Param("tagId") Long tagId);

    List<Tags> getTagsListByArticleId(@Param("articleId") Long articleId);
}