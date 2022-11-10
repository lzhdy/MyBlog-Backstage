package com.lzhdy.VO.home;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author lzhdy
 * @date 2022/11/9
 * @apiNote 首页分类情况展示
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TreeCategoryVo {
    private Long id;

    //分类名
    private String name;
    //描述
    private String description;
    //父分类id，如果没有父分类为-1
    private Long pid;
    //父分类名
    private String parentName;
    // 子分类
    private List<TreeCategoryVo> children;

}
