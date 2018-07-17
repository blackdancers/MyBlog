package com.github.fish.blog.core.biz;

import com.github.fish.blog.api.entity.Article;
import com.github.fish.blog.core.dao.ArticleMapper;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("articleBiz")
@Transactional(readOnly = true)
public class ArticleBiz {

    @Resource
    private ArticleMapper articleMapper;


    public Article getArticleById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Article> getArticlePageBySearch(Article article, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());
        List<Article> dataList = articleMapper.getArticlePageBySearch(article);
        return new PageInfo<>(dataList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Long addArticle(Article article) throws BaseBizException {
        articleMapper.insert(article);
        return article.getId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateArticle(Article article) throws BaseBizException {
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteArticleById(Long id) throws BaseBizException {
        return articleMapper.deleteByPrimaryKey(id);
    }
}
