package com.github.fish.web.index.controller;


import com.github.fish.blog.api.entity.Article;
import com.github.fish.blog.api.entity.Classification;
import com.github.fish.blog.api.entity.Tags;
import com.github.fish.blog.api.service.ArticleService;
import com.github.fish.blog.api.service.ClassificationService;
import com.github.fish.blog.api.service.TagsService;
import com.github.fish.common.constant.IConstInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private ArticleService articleService;
    @Resource
    private ClassificationService classificationService;
    @Resource
    private TagsService tagsService;

    @GetMapping("")
    public String blogIndex(@PageableDefault(size = 10, sort = {"updateDate"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        //文章列表
        PageInfo<Article> page = articleService.getArticleListByPage(pageable);
        model.addAttribute("page",page);

        List<Classification> classList = classificationService.getClassificationList(pageable);
        model.addAttribute("classList",classList); //所属分类

        List<Tags> tagsList = tagsService.getTagsList(pageable);
        model.addAttribute("tagsList",tagsList); //所有标签

        //推荐博客
        Article article = new Article();
        article.setIsRecommend(IConstInfo.ONE);
        List<Article> recommendBlogList = articleService.getArticleList(article,pageable);
        model.addAttribute("page",page);
        return "index";
    }

}
