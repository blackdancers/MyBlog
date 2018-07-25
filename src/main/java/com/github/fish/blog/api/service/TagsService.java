package com.github.fish.blog.api.service;

import com.github.fish.blog.api.entity.Tags;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagsService {

    public Long addTags(Tags tags) throws BaseBizException;

    public int updateTagsById(Tags tags) throws BaseBizException;

    public int deleteTagsById(Long id) throws BaseBizException;

    public Tags getTagsById(Long id) throws BaseBizException;

    public PageInfo<Tags> getTagsListByPage(Pageable pageable);

    public Boolean getTagsByName(String tagName);

    public List<Tags> getTagsList();

    public List<Tags> getTagsList(Pageable pageable);
}
