package com.github.fish.blog.api.entity;

import com.github.fish.common.entity.AbstractBaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

public class Classification extends AbstractBaseEntity {
    /**
     * 分类名称
     */
    @Column(name = "class_name")
    @NotBlank(message = "分类名称不能为空")
    private String className;

    /**
     * 关联文章数量
     */
    @Transient
    private int classNum;


    /**
     * 获取分类名称
     *
     * @return class_name - 分类名称
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置分类名称
     *
     * @param className 分类名称
     */
    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }
}