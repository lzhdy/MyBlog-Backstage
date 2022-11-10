package com.lzhdy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzhdy.VO.home.TreeCategoryVo;
import com.lzhdy.domain.ResponseResult;
import com.lzhdy.domain.entity.Category;
import com.lzhdy.mapper.CategoryMapper;
import com.lzhdy.service.CategoryService;
import com.lzhdy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author lzhdy
 * @since 2022-11-08 21:20:29
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private List<TreeCategoryVo> builderTree(List<TreeCategoryVo> treeCategoryVos, TreeCategoryVo parentCategory) {
        return treeCategoryVos.stream()
                .filter(treeCategoryVo -> treeCategoryVo.getPid().equals(parentCategory.getId()))
                .map(treeCategoryVo -> treeCategoryVo.setChildren(getChildren(treeCategoryVo, treeCategoryVos)).setParentName(parentCategory.getName()))
                .collect(Collectors.toList());
    }
    private List<TreeCategoryVo> getChildren(TreeCategoryVo treeCategoryVo, List<TreeCategoryVo> treeCategoryVos) {
        return treeCategoryVos.stream()
                .filter(m -> m.getPid().equals(treeCategoryVo.getId()))
                .map(m -> m.setChildren(getChildren(m,treeCategoryVos)).setParentName(treeCategoryVo.getName()))
                .collect(Collectors.toList());
    }
    @Override
    public ResponseResult<List<TreeCategoryVo>> getHomeCategory() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus, "1");
        List<Category> categoryList = list(queryWrapper);
        List<TreeCategoryVo> treeCategoryVos = BeanCopyUtils.beansCopyList(categoryList, TreeCategoryVo.class);
        List<TreeCategoryVo> root = treeCategoryVos.stream().filter(treeCategoryVo -> treeCategoryVo.getPid().equals(-1L)).toList();
        List<TreeCategoryVo> result = new ArrayList<>();
        for (TreeCategoryVo treeCategoryVo : root) {
            result.addAll(builderTree(treeCategoryVos, treeCategoryVo));
        }
        return ResponseResult.okResult(result);
    }

}

