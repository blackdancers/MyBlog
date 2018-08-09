package com.github.fish.web.blog.controller;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.blog.api.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 博文归档
 */
@Controller
public class ArchiveController {


    @Resource
    private ArticleService articleService;

    /**
     * @param model
     * @return
     */
    @GetMapping("archive")
    public String archiveListYear(Model model) {
        Map<String, List<Article>> archiveMap = articleService.getArchiveListByYear();
        model.addAttribute("archiveMap",archiveMap);
        int count = articleService.getArticleCount();
        model.addAttribute("count",count);
        return "archives";
    }


}
