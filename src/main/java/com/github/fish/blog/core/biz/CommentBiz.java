package com.github.fish.blog.core.biz;

import com.github.fish.blog.api.entity.Comment;
import com.github.fish.blog.core.dao.CommentMapper;
import com.github.fish.common.constant.IConstInfo;
import com.github.fish.common.enums.Module;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.fish.common.utils.JsonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("commentBiz")
public class CommentBiz {

    @Resource
    private CommentMapper commentMapper;

    /**
     * @param articleId
     * @return
     */
    public List<Comment> getCommentListByArticleId(Long articleId) {
        List<Comment> commentList = commentMapper.getCommentListByArticleId(articleId); //一级评论
        List<Comment> comments = eachComment(commentList);
        return comments;
    }

    /**
     * 循环每个顶级的评论节点
     */
    private List<Comment> eachComment(List<Comment> commentList) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : commentList) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        combineChildren(commentsView);
        return commentsView;
    }
    //临时存放子级评论的集合
    private List<Comment> temp = new ArrayList<>();

    /**
     * 合并评论的各层子代评论到第一级子代集合中
     *
     * @param comments root根节点，blog不为空的对象集合
     */
    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> subCommentList = comment.getSubCommentList();
            for (Comment subComment : subCommentList) {
                recursively(subComment);//循环迭代，找出子代，存放在temp集合中
            }
            //修改顶级节点的子代集合为迭代之后的集合,根据一级评论 添加下面所有评论
            comment.setSubCommentList(temp);
            //清除临时存放区
            temp = new ArrayList<>();
        }
    }


    /**
     * 递归迭代
     */
    private void recursively(Comment comment) {
        temp.add(comment); //添加当前评论到 子级评论集合中
        List<Comment> commentList = commentMapper.getSubCommentListByCommentId(comment.getId());  //该二级评论的三级评论
        comment.setSubCommentList(commentList);
        if (null != comment.getSubCommentList() && comment.getSubCommentList().size() > 0) {
            for (Comment subComment : comment.getSubCommentList()) {
                List<Comment> fourCommentList = commentMapper.getSubCommentListByCommentId(subComment.getId());  //三级评论的子级评论
                subComment.setSubCommentList(fourCommentList);
                if (null != subComment.getSubCommentList() && subComment.getSubCommentList().size() > 0){
                    recursively(subComment);
                }else {
                    temp.add(subComment);//避免重复添加
                }
            }
        }
    }


    /**
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
