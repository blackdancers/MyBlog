package com.github.fish.blog.core.biz;

import com.github.fish.blog.api.entity.Tags;
import com.github.fish.blog.core.dao.TagsMapper;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("tagsBiz")
public class TagsBiz {

    @Resource
    private TagsMapper tagsMapper;

    public Long addTags(Tags tags) throws BaseBizException {
        tagsMapper.insert(tags);
        return tags.getId();
    }

    public int updateTagsById(Tags tags) throws BaseBizException {
        return tagsMapper.updateByPrimaryKeySelective(tags);
    }

    public int deleteTagsById(Long id) throws BaseBizException {
        return tagsMapper.deleteByPrimaryKey(id);
    }

    public Tags getTagsById(Long id) throws BaseBizException {
        return tagsMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Tags> getTagsListByPage(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
        List<Tags> dataList = tagsMapper.selectAll();
        return new PageInfo<>(dataList);
    }

    public Boolean getTagsByName(String tagName) {
        return tagsMapper.getTagsByName(tagName);
    }
    public List<Tags> getTagsList() {
        return tagsMapper.selectAll();
    }

    public List<Tags> getTagsList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
        return tagsMapper.selectAll();
    }
}
