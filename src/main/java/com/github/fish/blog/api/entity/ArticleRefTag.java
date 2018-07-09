package com.github.fish.blog.api.entity;

import com.github.fish.common.entity.AbstractBaseEntity;
import javax.persistence.*;

@Table(name = "article_ref_tag")
public class ArticleRefTag extends AbstractBaseEntity {
    /**
     * 文章ID
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 标签ID
     */
    @Column(name = "tag_id")
    private Long tagId;

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
     * 获取标签ID
     *
     * @return tag_id - 标签ID
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * 设置标签ID
     *
     * @param tagId 标签ID
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}