package com.github.fish.blog.core.biz;

import com.github.fish.blog.api.entity.Comment;
import com.github.fish.blog.core.dao.CommentMapper;
import com.github.fish.common.constant.IConstInfo;
import com.github.fish.common.enums.Module;
import com.github.fish.common.exceptions.BaseBizException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("commentBiz")
public class CommentBiz {

    @Resource
    private CommentMapper commentMapper;

    /**
     *
     * @param articleId
     * @return
     */
    public List<Comment> getCommentListByArticleId(Long articleId) {
        return commentMapper.getCommentListByArticleId(articleId);
    }

    /**
     *
     * @param comment
     * @return
     * @throws BaseBizException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Long saveComment(Comment comment) throws BaseBizException {
        //处理评论子父级关系
        if (-1 == comment.getParentCommentId()) {  // ===>> commentId
            comment.setParentCommentId(null);
        }
        comment.setCreateDate(new Date());
        int res = commentMapper.insert(comment);
        if (res <= 0) {
            throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL, "评论失败", Module.SYSTEM);
        }
        return comment.getId();
    }
}
