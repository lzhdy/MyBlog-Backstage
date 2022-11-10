package com.lzhdy.controller;

import com.lzhdy.VO.home.TreeCategoryVo;
import com.lzhdy.domain.ResponseResult;
import com.lzhdy.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lzhdy
 * @date 2022/11/9
 * @apiNote 分类controller
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    // 主页获取分类
    @GetMapping("/getHomeCategory")
    public ResponseResult<List<TreeCategoryVo>> getHomeCategory(){
        return categoryService.getHomeCategory();
    }


}
