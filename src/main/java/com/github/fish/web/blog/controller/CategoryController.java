package com.github.fish.web.blog.controller;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.blog.api.entity.Classification;
import com.github.fish.blog.api.service.ArticleService;
import com.github.fish.blog.api.service.ClassificationService;
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
 *
 *
 * 分类列表 categories
 */
@Controller
public class CategoryController {

    @Resource
    private ClassificationService classificationService;
    @Resource
    private ArticleService articleService;

    @GetMapping("/category")
    public String categoryList(@PageableDefault(size = 10, sort = {"updateDate"}, direction = Sort.Direction.DESC) Pageable pageable, Model model){
        List<Classification> classificationList = classificationService.getClassificationList(100);
        Long classId = null;
        if (!CollectionUtils.isEmpty(classificationList)){
            //默认取第一个
            classId =  classificationList.get(0).getId();
        }
        Article param = new Article();
        param.setClassId(classId);
        PageInfo<Article> articlePageList = articleService.getArticlePageBySearch(param, pageable);

        model.addAttribute("activeId",classId);//checked
        model.addAttribute("page",articlePageList);
        model.addAttribute("classList",classificationList); //所属分类
        return "categories";
    }
    @GetMapping("/category/{id}")
    public String categoryList(@PageableDefault(size = 10, sort = {"updateDate"}, direction = Sort.Direction.DESC) Pageable pageable,
                               @PathVariable(required = false) Long id, Model model){
        List<Classification> classificationList = classificationService.getClassificationList(100);
        if (!CollectionUtils.isEmpty(classificationList)){
            if (null == id ){
                //默认取第一个
                id =  classificationList.get(0).getId();
            }
        }
        Article param = new Article();
        param.setClassId(id);
        PageInfo<Article> articlePageList = articleService.getArticlePageBySearch(param, pageable);

        model.addAttribute("activeId",id);//checked
        model.addAttribute("page",articlePageList);
        model.addAttribute("classList",classificationList); //所属分类
        return "categories";
    }
}
