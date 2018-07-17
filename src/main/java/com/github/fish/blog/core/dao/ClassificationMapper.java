package com.github.fish.blog.core.dao;

import com.github.fish.blog.api.entity.Classification;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ClassificationMapper extends Mapper<Classification> {

    Boolean getClassificationByName(@Param("className") String className);
}