package com.github.fish.blog.core.dao;

import com.github.fish.blog.api.entity.Tags;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TagsMapper extends Mapper<Tags> {
    Boolean getTagsByName(@Param("tagName") String tagName);

    List<Tags> getTagsList(@Param("num") int num);
}