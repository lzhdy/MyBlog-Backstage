package com.lzhdy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzhdy.VO.home.TreeCategoryVo;
import com.lzhdy.domain.ResponseResult;
import com.lzhdy.domain.entity.Category;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-11-08 21:20:28
 */
public interface CategoryService extends IService<Category> {

    ResponseResult<List<TreeCategoryVo>> getHomeCategory();

}

