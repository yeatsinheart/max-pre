package com.max.base.dto.response;

import com.max.base.entity.RelationCategoryView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分类与视图关系
 *
 * @author zane
 * @since 2019-08-28
 */
@Data

@ApiModel(value = "RelationCategoryViewResponseDto响应对象", description = "分类与视图关系")
public class RelationCategoryViewResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "分类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "视图ID")
    private Integer viewId;

    public RelationCategoryView toRelationCategoryView() {
        RelationCategoryView relationCategoryView = new RelationCategoryView();
        relationCategoryView.setId(this.id);
        relationCategoryView.setCategoryId(this.categoryId);
        relationCategoryView.setViewId(this.viewId);
        return relationCategoryView;
    }
}
