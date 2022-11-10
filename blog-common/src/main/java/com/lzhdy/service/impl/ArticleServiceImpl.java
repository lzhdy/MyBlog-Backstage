package com.lzhdy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzhdy.VO.home.ArticleListVo;
import com.lzhdy.VO.home.ArticleVo;
import com.lzhdy.domain.ResponseResult;
import com.lzhdy.domain.entity.Article;
import com.lzhdy.domain.entity.Category;
import com.lzhdy.mapper.ArticleMapper;
import com.lzhdy.service.ArticleService;
import com.lzhdy.service.CategoryService;
import com.lzhdy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 文章表(Article)表服务实现类
 *
 * @author lzhdy
 * @since 2022-11-07 15:32:07
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    private CategoryService categoryService;

    // 填充分类名称
    private void getCategoryNames(Page<Article> page){
        //从分类表中查询分类名称
        LambdaQueryWrapper<Category> query = new LambdaQueryWrapper<>();
        query.eq(Category::getStatus, "1");
        List<Category> allCategories = categoryService.list(query);

        for (Article article : page.getRecords()) {
            List<String> categoryNames = new ArrayList<>();
            Category category = categoryService.getById(article.getCategoryId());
            categoryNames.add(category.getName());
            getTreeList(allCategories, category.getPid(), categoryNames);
            article.setCategoryName(categoryNames);
        }
    }
    private void getTreeList(List<Category> allCategories, Long pid, List<String> categoryNames) {
        if (pid == -1){
            return;
        }
        for (Category category : allCategories) {
            if (Objects.equals(category.getId(), pid)){
                categoryNames.add(category.getName());
                if (category.getPid() != -1){
                    getTreeList(allCategories, category.getPid(),categoryNames);
                }
            }
        }
    }

    @Override
    public ResponseResult<List<ArticleVo>> getTopArticle() {

        // 查询置顶文章,
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getIsTop, "1");
        // 查询已发布文章
        queryWrapper.eq(Article::getStatus, "1");
        // 按照浏览量排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 限制显示10篇
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);

        //从分类表中查询分类名称
        getCategoryNames(page);


        // 封装成ResponseResult返回
        List<ArticleVo> articleVos = BeanCopyUtils.beansCopyList(page.getRecords(), ArticleVo.class);
        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult<ArticleListVo> getArticleList(Integer pageNumber,Integer pageSize) {
        // 获取文章,按时间排到序
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 查询已发布文章
        queryWrapper.eq(Article::getStatus, "1");
        // 按照创建时间排序
        queryWrapper.orderByDesc(Article::getCreateTime);
        // 分页查询
        Page<Article> page = new Page<>(pageNumber, pageSize);
        page(page, queryWrapper);

        getCategoryNames(page);


        List<ArticleVo> articleVos = BeanCopyUtils.beansCopyList(page.getRecords(), ArticleVo.class);


        return ResponseResult.okResult(new ArticleListVo(articleVos, pageNumber, pageSize, page.getTotal()));
    }
}

