package com.github.fish.web.admin.controller;


import com.github.fish.blog.api.entity.Article;
import com.github.fish.blog.api.entity.Classification;
import com.github.fish.blog.api.entity.SystemUser;
import com.github.fish.blog.api.entity.Tags;
import com.github.fish.blog.api.service.ArticleService;
import com.github.fish.blog.api.service.ClassificationService;
import com.github.fish.blog.api.service.TagsService;
import com.github.fish.common.constant.IConstInfo;
import com.github.fish.common.utils.JsonUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin")
public class BlogController {

    @Resource
    private ArticleService articleService;
    @Resource
    private ClassificationService classificationService;
    @Resource
    private TagsService tagsService;

    /**
     * 文章列表页
     * @param pageable
     * @param article
     * @param model
     * @return
     */
    @GetMapping("blog")
    public String blogList(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                         Pageable pageable, Article article, Model model) {
        PageInfo<Article> articlePageBySearch = articleService.getArticlePageBySearch(article, pageable);
        List<Classification> classList = classificationService.getClassificationList();
        model.addAttribute("classList",classList); //所属分类
        model.addAttribute("page",articlePageBySearch);
        System.out.println(JsonUtil.obj2Json(articlePageBySearch));
        return "admin/blog";
    }

    /**
     * 查询列表并重新渲染列表区域
     * @param pageable
     * @param article
     * @param model
     * @return
     */
    @PostMapping("blog/search")
    public String blogSearchList(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Article article, Model model) {
        PageInfo<Article> articlePageBySearch = articleService.getArticlePageBySearch(article, pageable);
        model.addAttribute("page",articlePageBySearch);
        return "admin/blog :: blogList"; //thymeleaf模板
    }

    /**
     * 文章添加页面
     * @return
     */
    @GetMapping("blog/add")
    public String addBlogUI(Model model) {
        List<Classification> classList = classificationService.getClassificationList();
        model.addAttribute("classList",classList); //所属分类
        List<Tags> tagsList = tagsService.getTagsList();
        model.addAttribute("tagsList",tagsList); //所有标签
        model.addAttribute("article",new Article());
        return "admin/addBlog";
    }

    @PostMapping("blog/add")
    public String addBlog(@Valid Article article, BindingResult result, HttpSession session, RedirectAttributes attributes) {
        Object sessionData = session.getAttribute(IConstInfo.CURRENT_USER);
        if (null == sessionData){
            attributes.addFlashAttribute("message", "登录过期");
            return "redirect:admin"; //登录页面
        }
        if(null != article && StringUtils.isNotBlank(article.getArticleName())){
            Boolean res = articleService.getArticleByName(article.getArticleName());
            if (res){
                result.rejectValue("articleName","message","文章标题重复");
            }
        }
        article.setUserId(((SystemUser)sessionData).getId());
        Long id = articleService.addArticle(article);
        if (null != id) {
            attributes.addFlashAttribute("message", "添加成功");
        } else {
            attributes.addFlashAttribute("message", "添加失败");
        }
        return "redirect:/admin/blog";
    }

}
