package com.github.fish.blog.api.entity;

import com.github.fish.common.entity.AbstractBaseEntity;
import javax.persistence.*;

public class Tags extends AbstractBaseEntity {
    /**
     * 标签名称
     */
    @Column(name = "tag_name")
    private String tagName;

    /**
     * 获取标签名称
     *
     * @return tag_name - 标签名称
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签名称
     *
     * @param tagName 标签名称
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }
}