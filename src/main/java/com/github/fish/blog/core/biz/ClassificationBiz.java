package com.github.fish.blog.core.biz;


import com.github.fish.blog.api.entity.Classification;
import com.github.fish.blog.core.dao.ClassificationMapper;
import com.github.fish.common.constant.IConstInfo;
import com.github.fish.common.enums.Module;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("classificationBiz")
@Transactional(readOnly = true)
public class ClassificationBiz {
    @Resource
    private ClassificationMapper classificationMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Long addClassification(Classification classification) throws BaseBizException {
        int res = classificationMapper.insert(classification);
        if (res <= 0){
            throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL,"添加失败", Module.SYSTEM);
        }
        return classification.getId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateClassificationById(Classification classification) throws BaseBizException {
        int res = classificationMapper.updateByPrimaryKeySelective(classification);
//        if (res <= 0){
//            throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL,"更新失败", Module.SYSTEM);
//        }
        return res;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteClassificationById(Long id) throws BaseBizException {
        int res = classificationMapper.deleteByPrimaryKey(id);
//        if (res <= 0){
//            throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL,"删除失败", Module.SYSTEM);
//        }
        return res;
    }

    public Classification getClassificationById(Long id) throws BaseBizException {
        return classificationMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Classification> getClassificationListByPage(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
        List<Classification> dataList = classificationMapper.selectAll();
        return new PageInfo<>(dataList);
    }

    public List<Classification> getClassificationList() {
        return classificationMapper.selectAll();
    }

    public Boolean getClassificationByName(String className) {
        return classificationMapper.getClassificationByName(className);
    }

    public List<Classification> getClassificationList(int num) {
        return classificationMapper.getClassificationList(num);
    }
}
