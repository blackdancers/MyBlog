package com.github.fish.web.blog.controller;


import com.github.fish.blog.api.entity.Article;
import com.github.fish.blog.api.entity.Comment;
import com.github.fish.blog.api.entity.SystemUser;
import com.github.fish.blog.api.service.ArticleService;
import com.github.fish.blog.api.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 文章评论
 */
@Controller
public class CommentController {

    @Resource
    private CommentService commentService;
    @Resource
    private ArticleService articleService;

    @Value("${comment.avatar}")
    private String avatar;

    /**
     * @param articleId
     * @param model
     * @return
     */
    @GetMapping("/comments/{articleId}")
    public String commentList(@PathVariable Long articleId, Model model) {
        List<Comment> commentList = commentService.getCommentListByArticleId(articleId);
        model.addAttribute("commentList", commentList);
        return "blog :: commentList";
    }


    /**
     * 发表评论
     *
     * @param comment
     * @return
     */
    @PostMapping("/comments")
    public String postComment(Comment comment, HttpSession session) {
        SystemUser systemUser = (SystemUser) session.getAttribute("systemUser");
        Article article = articleService.getArticleById(comment.getArticleId());
        if (null != article && comment.getArticleId().equals(article.getId())) {
            comment.setAvatar(avatar);
            if (null != systemUser) {
                comment.setAdminComment(true);
                comment.setAvatar(systemUser.getUserAvatar());
                comment.setNickName(systemUser.getUserName());
            }
            commentService.saveComment(comment);
        }
        return "redirect:/comments/" + comment.getArticleId();
    }
}
