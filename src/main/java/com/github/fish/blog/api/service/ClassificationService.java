package com.github.fish.blog.api.service;

import com.github.fish.blog.api.entity.Classification;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 博客分类
 */
public interface ClassificationService {


    public Long addClassification(Classification classification) throws BaseBizException;

    public int updateClassificationById(Classification classification) throws BaseBizException;

    public int deleteClassificationById(Long id) throws BaseBizException;

    public Classification getClassificationById(Long id) throws BaseBizException;

    public PageInfo<Classification> getClassificationListByPage(Pageable pageable);

    public List<Classification> getClassificationList();

    public Boolean getClassificationByName(String className);
}
