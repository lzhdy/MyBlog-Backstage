package com.lzhdy.VO.home;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author lzhdy
 * @date 2022/11/7
 * @apiNote 首页置顶文章展示
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {

    private Long id;
    //标题
    private String title;
    //文章字数
    private Long wordage;
    //文章摘要
    private String summary;
    //分类名
    private List<String> categoryName;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;
    // 创建时间
    private Date createTime;
}
