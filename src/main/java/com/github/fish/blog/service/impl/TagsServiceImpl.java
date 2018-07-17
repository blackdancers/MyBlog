package com.github.fish.blog.service.impl;

import com.github.fish.blog.api.entity.Tags;
import com.github.fish.blog.api.service.TagsService;
import com.github.fish.blog.core.biz.TagsBiz;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by fishbaby on 7/16/2018.
 */
@Component("tagsService")
public class TagsServiceImpl implements TagsService {

    @Resource
    private TagsBiz tagsBiz;


    @Override
    public Long addTags(Tags tags) throws BaseBizException {
        return tagsBiz.addTags(tags);
    }

    @Override
    public int updateTagsById(Tags tags) throws BaseBizException {
        return tagsBiz.updateTagsById(tags);
    }

    @Override
    public int deleteTagsById(Long id) throws BaseBizException {
        return tagsBiz.deleteTagsById(id);
    }

    @Override
    public Tags getTagsById(Long id) throws BaseBizException {
        return tagsBiz.getTagsById(id);
    }

    @Override
    public PageInfo<Tags> getTagsListByPage(Pageable pageable) {
        return tagsBiz.getTagsListByPage(pageable);
    }

    @Override
    public Boolean getTagsByName(String tagName) {
        return tagsBiz.getTagsByName(tagName);
    }
}
