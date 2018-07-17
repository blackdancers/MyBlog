package com.github.fish.web.admin.controller;


import com.github.fish.blog.api.entity.Classification;
import com.github.fish.blog.api.service.ClassificationService;
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
public class ClassificationController {

    @Resource
    private ClassificationService classificationService;


    @GetMapping("category")
    public String categoryList(@PageableDefault(size = 12, sort = {"id"}, direction = Sort.Direction.DESC)
                                       Pageable pageable, Model model) {

//        Page<Classification> classifications = classificationService.getClassificationByPage(pageable);
//        model.addAttribute("page",classifications); //返回分页数据不全
        PageInfo<Classification> pageInfo = classificationService.getClassificationListByPage(pageable);
        System.out.println(JsonUtil.obj2Json(pageInfo));
        model.addAttribute("page", pageInfo);
        return "admin/categories";
    }

    /**
     * category/add 可以相同，但是请求方式不同
     * @param model
     * @return
     */
    @GetMapping("category/add")
    public String addCategoryUI(Model model) {
        model.addAttribute("classification",new Classification());
        return "admin/addCategory";
    }

    @PostMapping("category/add")
    public String addCategory(@Valid Classification classification, BindingResult result, RedirectAttributes attributes) {

        if(null != classification && StringUtils.isNoneBlank(classification.getClassName())){
            Boolean res = classificationService.getClassificationByName(classification.getClassName());
            if (res){
                result.rejectValue("className","message","分类名称已存在");
            }
        }
        if (result.hasErrors()){
            return "admin/addCategory";
        }

        Long id = classificationService.addClassification(classification);
        if (null != id) {
            attributes.addFlashAttribute("message", "添加成功");
        } else {
            attributes.addFlashAttribute("message", "添加失败");
        }
        return "redirect:/admin/category";
    }

    @GetMapping("category/update/{id}")
    public String updateCategoryUI(Model model,@PathVariable Long id) {
        Classification classification = classificationService.getClassificationById(id);
        model.addAttribute("classification",classification);
        return "admin/addCategory";
    }

    @PostMapping("category/update/{id}")
    public String updateCategory(@Valid Classification classification, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {

        if(null != classification && StringUtils.isNoneBlank(classification.getClassName())){
            Boolean res = classificationService.getClassificationByName(classification.getClassName());
            if (res){
                result.rejectValue("className","message","分类名称已存在");
            }
        }
        if (result.hasErrors()){
            return "admin/addCategory";
        }
        classification.setId(id);
        int res = classificationService.updateClassificationById(classification);
        if (res <= 0) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/category";
    }

    @GetMapping("category/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes attributes) {
        int res = classificationService.deleteClassificationById(id);
        if (res <= 0) {
            attributes.addFlashAttribute("message", "删除失败");
        } else {
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/category";
    }

}
