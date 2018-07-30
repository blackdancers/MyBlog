package com.github.fish.blog.core.dao;

import com.github.fish.blog.api.entity.Comment;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CommentMapper extends Mapper<Comment> {
    List<Comment> getCommentListByArticleId(@Param("articleId") Long articleId);
}