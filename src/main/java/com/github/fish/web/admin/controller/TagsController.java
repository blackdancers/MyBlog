package com.github.fish.web.admin.controller;


import com.github.fish.blog.api.entity.Tags;
import com.github.fish.blog.api.service.TagsService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("admin")
public class TagsController {

    @Resource
    private TagsService tagsService;


    @GetMapping("tags")
    public String tagsList(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                       Pageable pageable, Model model) {
        PageInfo<Tags> pageInfo = tagsService.getTagsListByPage(pageable);
        System.out.println(JsonUtil.obj2Json(pageInfo));
        model.addAttribute("page", pageInfo);
        return "admin/tags";
    }

    @GetMapping("tags/add")
    public String addTagsUI(Model model) {
        model.addAttribute("tags",new Tags());
        return "admin/addTags";
    }

    @PostMapping("tags/add")
    public String addTags(@Valid Tags tags, BindingResult result, RedirectAttributes attributes) {

        if(null != tags && StringUtils.isNotBlank(tags.getTagName())){
            Boolean res = tagsService.getTagsByName(tags.getTagName());
            if (res){
                result.rejectValue("tagName","message","标签名称已存在");
            }
        }
        if (result.hasErrors()){
            return "admin/addTags";
        }

        Long id = tagsService.addTags(tags);
        if (null != id) {
            attributes.addFlashAttribute("message", "添加成功");
        } else {
            attributes.addFlashAttribute("message", "添加失败");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("tags/update/{id}")
    public String updateTagsUI(Model model,@PathVariable Long id) {
        Tags tags = tagsService.getTagsById(id);
        model.addAttribute("tags",tags);
        return "admin/addTags";
    }

    @PostMapping("tags/update/{id}")
    public String updateTags(@Valid Tags tags, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {

        if(null != tags && StringUtils.isNoneBlank(tags.getTagName())){
            Boolean res = tagsService.getTagsByName(tags.getTagName());
            if (res){
                result.rejectValue("tagName","message","标签名称已存在");
            }
        }
        if (result.hasErrors()){
            return "admin/addTags";
        }
        tags.setId(id);
        int res = tagsService.updateTagsById(tags);
        if (res <= 0) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("tags/delete/{id}")
    public String deleteTags(@PathVariable Long id, RedirectAttributes attributes) {
        int res = tagsService.deleteTagsById(id);
        if (res <= 0) {
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/tags";
    }

}
