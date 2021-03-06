package com.github.fish.blog.api.entity;

import com.github.fish.common.entity.AbstractBaseEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

public class Comment extends AbstractBaseEntity {
    /**
     * 文章ID
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 评论父ID
     */
    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 评论内容
     */
    @Column(name = "comment_content")
    private String commentContent;

    /**
     * 子级所有评论
     */
    @Transient
    private List<Comment> subCommentList;


    @Transient
    private Comment parentComment;


    /**
     * 管理员评论
     */
    @Column(name = "admin_comment")
    private Boolean adminComment;

    /**
     *  展示效果 一二级扁平的展示
     *  一级
     *   |
     *   |-- 所有二级
     *
     *
     */

    /**
     * 获取文章ID
     *
     * @return article_id - 文章ID
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 设置文章ID
     *
     * @param articleId 文章ID
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取评论父ID
     *
     * @return parent_comment_id - 评论父ID
     */
    public Long getParentCommentId() {
        return parentCommentId;
    }

    /**
     * 设置评论父ID
     *
     * @param parentCommentId 评论父ID
     */
    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    /**
     * 获取用户昵称
     *
     * @return nick_name - 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户昵称
     *
     * @param nickName 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取用户头像
     *
     * @return avatar - 用户头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置用户头像
     *
     * @param avatar 用户头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 获取用户邮箱
     *
     * @return email - 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户邮箱
     *
     * @param email 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取评论内容
     *
     * @return comment_content - 评论内容
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置评论内容
     *
     * @param commentContent 评论内容
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public List<Comment> getSubCommentList() {
        return subCommentList;
    }

    public void setSubCommentList(List<Comment> subCommentList) {
        this.subCommentList = subCommentList;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
    }
}