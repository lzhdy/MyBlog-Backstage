package com.lzhdy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzhdy.VO.home.ArticleListVo;
import com.lzhdy.VO.home.ArticleVo;
import com.lzhdy.domain.ResponseResult;
import com.lzhdy.domain.entity.Article;

import java.util.List;


/**
 * 文章表(Article)表服务接口
 *
 * @author lzhdy
 * @since 2022-11-07 15:32:06
 */
public interface ArticleService extends IService<Article> {

    ResponseResult<List<ArticleVo>> getTopArticle();

    ResponseResult<ArticleListVo> getArticleList(Integer pageNumber,Integer pageSize);
}

