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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String indexBlogs(@PageableDefault(size = 10, sort = {"updateDate"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        //文章列表
        PageInfo<Article> page = articleService.getArticleListByPage(pageable);
        model.addAttribute("page", page);

        List<Classification> classList = classificationService.getClassificationList(IConstInfo.DEFAULT_NUM);
        model.addAttribute("classList", classList); //所属分类

        List<Tags> tagsList = tagsService.getTagsList(IConstInfo.DEFAULT_NUM);
        model.addAttribute("tagsList", tagsList); //所有标签

        //推荐博客
        Article article = new Article();
        article.setIsRecommend(IConstInfo.ONE);
        List<Article> recommendBlogList = articleService.getArticleList(article, IConstInfo.DEFAULT_NUM);
        model.addAttribute("recommendBlogList", recommendBlogList);
        return "index";
    }

    /**
     * 查询文章标题或内容
     *
     * @param pageable
     * @param keyWords
     * @param model
     * @return
     */
    @PostMapping("/search")
    public String searchBolgs(@PageableDefault(size = 10, sort = {"updateDate"}, direction = Sort.Direction.DESC) Pageable pageable,
                              @RequestParam String keyWords, Model model) {

        //设置搜索内容
        Article article = new Article();
        article.setKeyWords(keyWords);
        //文章列表
        PageInfo<Article> page = articleService.getArticlePageBySearch(article, pageable);
        model.addAttribute("page", page);
        model.addAttribute("keyWords", keyWords); //查询参数
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable("id") Long articleId, Model model) {

        //文章列表
        Article article = articleService.getArticleContentById(articleId);
        model.addAttribute("article", article);

        List<Tags> tagsList = tagsService.getTagsListByArticleId(articleId);
        model.addAttribute("tagsList", tagsList); //所有标签
        return "blog";
    }

    @GetMapping("/about")
    public String aboutMe(Model model) {
        return "about";
    }


    /**
     * 最新文章列表
     */
    @GetMapping("/footer/lastArticleList")
    public String lastArticleList(Model model) {
        Article article = new Article();
        article.setIsRecommend(IConstInfo.ONE);
        List<Article> lastArticleList = articleService.getArticleList(article, 3);
        model.addAttribute("lastArticleList", lastArticleList);
        return "_fragments :: lastArticleList";
    }


}
