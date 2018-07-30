package com.github.fish.blog.api.service;


import com.github.fish.blog.api.entity.Comment;
import com.github.fish.common.exceptions.BaseBizException;

import java.util.List;

public interface CommentService {


    List<Comment> getCommentListByArticleId(Long articleId);

    Long saveComment(Comment comment) throws BaseBizException;
}
