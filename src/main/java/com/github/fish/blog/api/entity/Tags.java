package com.github.fish.blog.api.entity;

import com.github.fish.common.entity.AbstractBaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

public class Tags extends AbstractBaseEntity {
    /**
     * 标签名称
     */
    @Column(name = "tag_name")
    @NotBlank(message = "标签名称不能为空")
    private String tagName;

    /**
     * 关联文章数量
     */
    @Transient
    private int tagNum;


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

    public int getTagNum() {
        return tagNum;
    }

    public void setTagNum(int tagNum) {
        this.tagNum = tagNum;
    }
}