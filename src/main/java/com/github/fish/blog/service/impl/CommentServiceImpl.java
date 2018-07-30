package com.github.fish.blog.service.impl;

import com.github.fish.blog.api.entity.Comment;
import com.github.fish.blog.api.service.CommentService;
import com.github.fish.blog.core.biz.CommentBiz;
import com.github.fish.common.exceptions.BaseBizException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fishbaby on 7/30/2018.
 */
@Component("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentBiz commentBiz;

    @Override
    public List<Comment> getCommentListByArticleId(Long articleId) {
        return commentBiz.getCommentListByArticleId(articleId);
    }

    @Override
    public Long saveComment(Comment comment) throws BaseBizException {
        return commentBiz.saveComment(comment);
    }
}
