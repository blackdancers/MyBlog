package com.github.fish.blog.core.biz;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.blog.api.entity.ArticleRefTag;
import com.github.fish.blog.core.dao.ArticleMapper;
import com.github.fish.blog.core.dao.ArticleRefTagMapper;
import com.github.fish.common.constant.IConstInfo;
import com.github.fish.common.enums.Module;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.fish.common.utils.MarkdownUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("articleBiz")
@Transactional(readOnly = true)
public class ArticleBiz {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleRefTagMapper articleRefTagMapper;


    public Article getArticleById(Long id) {
        //Article article = articleMapper.selectByPrimaryKey(id);
        Article article = articleMapper.getArticleById(id);
        String tagsIds = articleRefTagMapper.getTagIdsByArticleId(id);
        article.setTagIds(tagsIds);
        return article;
    }

    public Article getArticleContentById(Long id) {
        Article article = articleMapper.getArticleById(id);
        String tagsIds = articleRefTagMapper.getTagIdsByArticleId(id);
        article.setTagIds(tagsIds);
        //markdown格式内容转成html格式
        String markdownContent = article.getArticleContent();
        if (StringUtils.isNotBlank(markdownContent)){
            String text = MarkdownUtil.markdownToHtmlExtensions(markdownContent);
            article.setArticleContent(text);
        }
        return article;
    }

    public PageInfo<Article> getArticlePageBySearch(Article article, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
        List<Article> dataList = articleMapper.getArticlePageBySearch(article);
        return new PageInfo<>(dataList);
    }

    public List<Article> getArticleList(Article article, int num) {
        return articleMapper.getArticleList(article,num);
    }

    public PageInfo<Article> getArticleListByPage(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
        List<Article> dataList = articleMapper.getArticleListByPage();
        return new PageInfo<>(dataList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Long addArticle(Article article) throws BaseBizException {
        article.setCreateDate(new Date());
        article.setViews(0);
        int res1 = articleMapper.insert(article);
        if (res1 <= 0){
            throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL,"添加失败", Module.SYSTEM);
        }
        //插入成功之后返回ID
        Long articleId = article.getId();
        if (StringUtils.isNotBlank(article.getTagIds())){
            String[] ids = article.getTagIds().split(",");
            for (int i = 0; i < ids.length; i++) {
                ArticleRefTag articleRefTag = new ArticleRefTag();
                articleRefTag.setArticleId(articleId);
                articleRefTag.setTagId(Long.valueOf(ids[i]));
                articleRefTagMapper.insert(articleRefTag);
            }
        }
        return articleId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateArticle(Article article) throws BaseBizException {
        //先删除标签，再添加
        articleRefTagMapper.deleteTagsByArticleId(article.getId());
        if (StringUtils.isNotBlank(article.getTagIds())){
            String[] ids = article.getTagIds().split(",");
            for (int i = 0; i < ids.length; i++) {
                ArticleRefTag articleRefTag = new ArticleRefTag();
                articleRefTag.setArticleId(article.getId());
                articleRefTag.setTagId(Long.valueOf(ids[i]));
                articleRefTagMapper.insert(articleRefTag);
            }
        }
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteArticleById(Long id) throws BaseBizException {
        return articleMapper.deleteByPrimaryKey(id);
    }

    public Boolean getArticleByName(String articleName) {
        return articleMapper.getArticleByName(articleName);
    }
}
