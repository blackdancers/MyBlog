package com.github.fish.blog.service.impl;

import com.github.fish.blog.api.entity.Classification;
import com.github.fish.blog.api.service.ClassificationService;
import com.github.fish.blog.core.biz.ClassificationBiz;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("classificationService")
public class ClassificationServiceImpl implements ClassificationService {

    @Resource
    private ClassificationBiz classificationBiz;

    @Override
    public Long addClassification(Classification classification) throws BaseBizException {
        return classificationBiz.addClassification(classification);
    }

    @Override
    public int updateClassificationById(Classification classification) throws BaseBizException {
        return classificationBiz.updateClassificationById(classification);
    }

    @Override
    public int deleteClassificationById(Long id) throws BaseBizException {
        return classificationBiz.deleteClassificationById(id);
    }

    @Override
    public Classification getClassificationById(Long id) throws BaseBizException {
        return classificationBiz.getClassificationById(id);
    }

    @Override
    public PageInfo<Classification> getClassificationListByPage(Pageable pageable) {
        return classificationBiz.getClassificationListByPage(pageable);
    }

    @Override
    public List<Classification> getClassificationList() {
        return classificationBiz.getClassificationList();
    }

    @Override
    public Boolean getClassificationByName(String className) {
        return classificationBiz.getClassificationByName(className);
    }

    @Override
    public List<Classification> getClassificationList(Pageable pageable) {
        return classificationBiz.getClassificationList(pageable);
    }
}
