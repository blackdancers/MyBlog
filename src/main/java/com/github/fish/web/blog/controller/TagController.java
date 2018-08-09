package com.github.fish.web.blog.controller;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.blog.api.entity.Tags;
import com.github.fish.blog.api.service.ArticleService;
import com.github.fish.blog.api.service.TagsService;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签列表页
 *
 */
@Controller
public class TagController {
    @Resource
    private TagsService tagsService;
    @Resource
    private ArticleService articleService;

    @GetMapping("/tag")
    public String tagIndex(@PageableDefault(size = 10, sort = {"updateDate"}, direction = Sort.Direction.DESC) Pageable pageable,Model model){
        List<Tags> tagsList = tagsService.getTagsList(100);
        Long tagId = null;
        if (!CollectionUtils.isEmpty(tagsList)){
            //默认取第一个
            tagId =  tagsList.get(0).getId();
        }
        Article param = new Article();
        param.setTagIds(tagId.toString());
        PageInfo<Article> articlePageList =articleService.getArticlePageByTagId(param, pageable);

        model.addAttribute("activeId",tagId);//checked
        model.addAttribute("page",articlePageList);
        model.addAttribute("tagsList",tagsList); //所属分类
        return "tags";
    }
    @GetMapping("/tag/{id}")
    public String tagList(@PageableDefault(size = 10, sort = {"updateDate"}, direction = Sort.Direction.DESC) Pageable pageable,
                               @PathVariable(required = false) Long id, Model model){
        List<Tags> tagsList = tagsService.getTagsList(100);
        if (!CollectionUtils.isEmpty(tagsList)){
            if (null == id){
                //默认取第一个
                id =  tagsList.get(0).getId();
            }
        }
        Article param = new Article();
        param.setTagIds(id.toString());
        PageInfo<Article> articlePageList =articleService.getArticlePageByTagId(param, pageable);

        model.addAttribute("activeId",id);//checked
        model.addAttribute("page",articlePageList);
        model.addAttribute("tagsList",tagsList); //所属分类
        return "tags";
    }
}
