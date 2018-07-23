package com.github.fish.blog.api.entity;

import com.github.fish.common.entity.AbstractBaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import javax.persistence.*;

public class Article extends AbstractBaseEntity {
    /**
     * 文章标题
     */
    @Column(name = "article_name")
    @NotBlank(message = "文章标题不能为空")
    private String articleName;

    /**
     * 封面图[url]
     */
    @Column(name = "article_cover")
    private String articleCover;

    /**
     * 标记
     */
    @Column(name = "article_flag")
    private String articleFlag;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 是否开启赞赏功能[1:是;0:否]
     */
    private String appreciation;

    /**
     * 转载声明是否开启[1:是;0:否]
     */
    @Column(name = "share_statement")
    private String shareStatement;

    /**
     * 是否可评论[1:是;0:否]
     */
    @Column(name = "is_comment")
    private String isComment;

    /**
     * 发布[1:是;0:否]
     */
    @Column(name = "is_issue")
    private String isIssue;

    /**
     * 推荐[1:是;0:否]
     */
    @Column(name = "is_recommend")
    private String isRecommend;

    /**
     * 分类ID
     */
    @Column(name = "class_id")
    private Long classId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

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
     * 文章内容
     */
    @Column(name = "article_content")
    private String articleContent;

    /**
     * 分类名称
     */
    @Transient
    private String className;

    /**
     * 标签集合
     */
    @Transient
    private String tagIds;



    /**
     * 获取文章标题
     *
     * @return article_name - 文章标题
     */
    public String getArticleName() {
        return articleName;
    }

    /**
     * 设置文章标题
     *
     * @param articleName 文章标题
     */
    public void setArticleName(String articleName) {
        this.articleName = articleName == null ? null : articleName.trim();
    }

    /**
     * 获取封面图[url]
     *
     * @return article_cover - 封面图[url]
     */
    public String getArticleCover() {
        return articleCover;
    }

    /**
     * 设置封面图[url]
     *
     * @param articleCover 封面图[url]
     */
    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover == null ? null : articleCover.trim();
    }

    /**
     * 获取标记
     *
     * @return article_flag - 标记
     */
    public String getArticleFlag() {
        return articleFlag;
    }

    /**
     * 设置标记
     *
     * @param articleFlag 标记
     */
    public void setArticleFlag(String articleFlag) {
        this.articleFlag = articleFlag == null ? null : articleFlag.trim();
    }

    /**
     * 获取浏览次数
     *
     * @return views - 浏览次数
     */
    public Integer getViews() {
        return views;
    }

    /**
     * 设置浏览次数
     *
     * @param views 浏览次数
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * 获取是否开启赞赏功能[1:是;0:否]
     *
     * @return appreciation - 是否开启赞赏功能[1:是;0:否]
     */
    public String getAppreciation() {
        return appreciation;
    }

    /**
     * 设置是否开启赞赏功能[1:是;0:否]
     *
     * @param appreciation 是否开启赞赏功能[1:是;0:否]
     */
    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation == null ? null : appreciation.trim();
    }

    /**
     * 获取转载声明是否开启[1:是;0:否]
     *
     * @return share_statement - 转载声明是否开启[1:是;0:否]
     */
    public String getShareStatement() {
        return shareStatement;
    }

    /**
     * 设置转载声明是否开启[1:是;0:否]
     *
     * @param shareStatement 转载声明是否开启[1:是;0:否]
     */
    public void setShareStatement(String shareStatement) {
        this.shareStatement = shareStatement == null ? null : shareStatement.trim();
    }

    /**
     * 获取是否可评论[1:是;0:否]
     *
     * @return is_comment - 是否可评论[1:是;0:否]
     */
    public String getIsComment() {
        return isComment;
    }

    /**
     * 设置是否可评论[1:是;0:否]
     *
     * @param isComment 是否可评论[1:是;0:否]
     */
    public void setIsComment(String isComment) {
        this.isComment = isComment == null ? null : isComment.trim();
    }

    /**
     * 获取发布[1:是;0:否]
     *
     * @return is_issue - 发布[1:是;0:否]
     */
    public String getIsIssue() {
        return isIssue;
    }

    /**
     * 设置发布[1:是;0:否]
     *
     * @param isIssue 发布[1:是;0:否]
     */
    public void setIsIssue(String isIssue) {
        this.isIssue = isIssue == null ? null : isIssue.trim();
    }

    /**
     * 获取推荐[1:是;0:否]
     *
     * @return is_recommend - 推荐[1:是;0:否]
     */
    public String getIsRecommend() {
        return isRecommend;
    }

    /**
     * 设置推荐[1:是;0:否]
     *
     * @param isRecommend 推荐[1:是;0:否]
     */
    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend == null ? null : isRecommend.trim();
    }

    /**
     * 获取分类ID
     *
     * @return class_id - 分类ID
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * 设置分类ID
     *
     * @param classId 分类ID
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * 获取文章内容
     *
     * @return article_content - 文章内容
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * 设置文章内容
     *
     * @param articleContent 文章内容
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }
}