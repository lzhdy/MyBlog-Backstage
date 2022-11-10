package com.lzhdy.VO.home;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lzhdy
 * @date 2022/11/9
 * @apiNote 文章列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {
    private List<ArticleVo> articleVoList;
    private Integer pageNumber;
    private Integer pageSize;
    private Long total;
}
