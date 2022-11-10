package com.lzhdy.controller;

import com.lzhdy.VO.home.ArticleListVo;
import com.lzhdy.VO.home.ArticleVo;
import com.lzhdy.domain.ResponseResult;
import com.lzhdy.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzhdy
 * @date 2022/11/7
 * @apiNote 文章controller
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    // 获取置顶文章列表
    @GetMapping("/top")
    public ResponseResult<List<ArticleVo>> getTopArticle() {
        return articleService.getTopArticle();
    }

    // 获取文章列表
    @GetMapping("/articleList")
    public ResponseResult<ArticleListVo> getArticleList(Integer pageNumber,Integer pageSize) {
        return articleService.getArticleList(pageNumber, pageSize);
    }
}
